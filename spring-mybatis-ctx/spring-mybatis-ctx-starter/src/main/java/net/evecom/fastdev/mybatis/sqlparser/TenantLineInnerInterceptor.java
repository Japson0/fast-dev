/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.sqlparser;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;

import java.util.List;

/**
 * 租户解析器，会对SQL里面的每一张表做租户限制
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月07日
 *
 * @author Japson Huang
 */
public class TenantLineInnerInterceptor extends com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor {


    public TenantLineInnerInterceptor(TenantLineHandler tenantLineHandler) {
        super(tenantLineHandler);
    }

    /**
     * 目前仅支持：in, between, >, <, =, !=等比较操作，
     *
     * @param expression
     * @param table
     * @return
     */
    protected Expression processTableAlias(Expression expression, Table table) {
        String tableAliasName;
        if (table.getAlias() == null) {
            tableAliasName = table.getName();
        } else {
            tableAliasName = table.getAlias().getName();
        }
        if (expression instanceof InExpression) {
            InExpression in = (InExpression) expression;
            if (in.getLeftExpression() instanceof Column) {
                setTableAliasNameForColumn((Column) in.getLeftExpression(), tableAliasName);
            }
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression compare = (BinaryExpression) expression;
            if (compare.getLeftExpression() instanceof Column) {
                setTableAliasNameForColumn((Column) compare.getLeftExpression(), tableAliasName);
            } else if (compare.getRightExpression() instanceof Column) {
                setTableAliasNameForColumn((Column) compare.getRightExpression(), tableAliasName);
            }
        } else if (expression instanceof Between) {
            Between between = (Between) expression;
            if (between.getLeftExpression() instanceof Column) {
                setTableAliasNameForColumn((Column) between.getLeftExpression(), tableAliasName);
            }
        }
        return expression;
    }

    private void setTableAliasNameForColumn(Column column, String tableAliasName) {
        column.setColumnName(tableAliasName + "." + column.getColumnName());
    }

    /**
     * select 语句处理
     */
    @Override
    public void processSelectBody(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody, false);
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSubSelect().getSelectBody() != null) {
                processSelectBody(withItem.getSubSelect().getSelectBody());
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
                operationList.getSelects().forEach(this::processSelectBody);
            }
        }
    }

    /**
     * 生成租户和数据权限where 条件
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月07日
     *
     * @author Japson Huang
     */
    protected Expression builderExpression(Expression currentExpression, Table table) {
        Expression appendExpression = buildTenant(table);
        if (currentExpression == null) {
            return appendExpression;
        }
        if (currentExpression instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) currentExpression;
            recursionDoExpression(binaryExpression);
        } else {
            doInnerExpression(currentExpression);
        }
        if (currentExpression instanceof OrExpression) {
            return new AndExpression(new Parenthesis(currentExpression), appendExpression);
        } else {
            return new AndExpression(currentExpression, appendExpression);
        }
    }

    /**
     * 递归查询每一个子条件，如 WHERE ID=1 AND P=2  AND K=3 。这时候旧版本回有BUG，无法对某个子查询做租户操作
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月09日
     *
     * @author Japson Huang
     */
    private void recursionDoExpression(Expression expression) {
        if (expression instanceof AndExpression || expression instanceof OrExpression) {
            recursionDoExpression(((BinaryExpression) expression).getLeftExpression());
            recursionDoExpression(((BinaryExpression) expression).getRightExpression());
        }
        doExpression(expression);
    }


    private void doInnerExpression(Expression expression) {
        if (expression instanceof InExpression) {
            InExpression inExp = (InExpression) expression;
            ItemsList rightItems = inExp.getRightItemsList();
            if (rightItems instanceof SubSelect) {
                processSelectBody(((SubSelect) rightItems).getSelectBody());
            }
        } else if (expression instanceof ExistsExpression) {
            Expression rightExpression = ((ExistsExpression) expression).getRightExpression();
            if (rightExpression instanceof SubSelect) {
                processSelectBody(((SubSelect) rightExpression).getSelectBody());
            }
        }
    }

    protected void doExpression(Expression expression) {
        if (expression instanceof FromItem) {
            processFromItem((FromItem) expression);
        } else {
            doInnerExpression(expression);
        }
    }

    /**
     * 生成where 条件
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月07日
     *
     * @author Japson Huang
     */
    protected Expression buildConditionExpress(Expression expression, Table table, String columnName) {
        Expression appendExpression;
        if (!(expression instanceof SupportsOldOracleJoinSyntax)) {
            appendExpression = new EqualsTo();
            ((EqualsTo) appendExpression).setLeftExpression(this.getAliasColumn(table, columnName));
            ((EqualsTo) appendExpression).setRightExpression(expression);
        } else {
            appendExpression = processTableAlias(expression, table);
        }
        return appendExpression;
    }

    /**
     * 生成租户条件
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月07日
     *
     * @author Japson Huang
     */
    protected Expression buildTenant(Table table) {
        if (getTenantLineHandler().ignoreTable(table.getName())) {
            return null;
        }
        final Expression tenantExpression = getTenantLineHandler().getTenantId();
        return buildConditionExpress(tenantExpression, table, this.getTenantLineHandler().getTenantIdColumn());
    }


    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable));
            if (addColumn) {
                plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(this.getTenantLineHandler().getTenantIdColumn())));
            }
        } else {
            processFromItem(fromItem);
        }
        List<Join> joins = plainSelect.getJoins();
        if (joins != null && joins.size() > 0) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
    }

    /**
     * 处理子查询等
     */
    protected void processFromItem(FromItem fromItem) {
        if (fromItem instanceof SubJoin) {
            SubJoin subJoin = (SubJoin) fromItem;
            if (subJoin.getJoinList() != null) {
                subJoin.getJoinList().forEach(this::processJoin);
            }
            if (subJoin.getLeft() != null) {
                processFromItem(subJoin.getLeft());
            }
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            if (subSelect.getSelectBody() != null) {
                processSelectBody(subSelect.getSelectBody());
            }
        } else if (fromItem instanceof ValuesList) {
            logger.debug("Perform a subquery, if you do not give us feedback");
        } else if (fromItem instanceof LateralSubSelect) {
            LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
            if (lateralSubSelect.getSubSelect() != null) {
                SubSelect subSelect = lateralSubSelect.getSubSelect();
                if (subSelect.getSelectBody() != null) {
                    processSelectBody(subSelect.getSelectBody());
                }
            }
        }
    }

    /**
     * 处理联接语句
     */
    protected void processJoin(Join join) {
        if (join.getRightItem() instanceof Table) {
            Table fromTable = (Table) join.getRightItem();
            if (this.getTenantLineHandler().ignoreTable(fromTable.getName())) {
                // 过滤退出执行
                return;
            }
            join.setOnExpression(builderExpression(join.getOnExpression(), fromTable));
        }
    }

    @Override
    protected void processDelete(Delete delete, int index, String sql, Object obj) {
        if (getTenantLineHandler().ignoreTable(delete.getTable().getName())) {
            // 过滤退出执行
            return;
        }
        delete.setWhere(this.builderExpression(delete.getWhere(), delete.getTable()));
    }


    /**
     * update 语句处理
     */
    @Override
    protected void processUpdate(Update update, int index, String sql, Object obj) {
        final Table table = update.getTable();
        if (getTenantLineHandler().ignoreTable(table.getName())) {
            // 过滤退出执行
            return;
        }
        update.setWhere(this.builderExpression(update.getWhere(), update.getTable()));
    }


    protected Column getAliasColumn(Table table, String columnName) {
        StringBuilder column = new StringBuilder();
        if (table.getAlias() != null) {
            column.append(table.getAlias().getName());
        } else {
            column.append(table.getName());
        }
        column.append(StringPool.DOT).append(columnName);
        return new Column(column.toString());
    }


}
