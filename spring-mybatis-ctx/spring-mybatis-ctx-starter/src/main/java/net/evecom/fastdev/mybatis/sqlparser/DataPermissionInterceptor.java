/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.sqlparser;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.cnfexpression.MultiAndExpression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <P><B>数据权限解析器，只会对最外层的主表做数据权限:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DataPermissionInterceptor extends TenantLineInnerInterceptor {

    /**
     * 权限操作类
     */
    private PermissionHandler permissionHandler;

    public DataPermissionInterceptor(PermissionHandler permissionHandler) {
        this.permissionHandler = permissionHandler;
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
            processPlainSelect((PlainSelect) selectBody);
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
        Expression appendExpression = buildDataPermission(table);
        if (appendExpression == null) {
            return currentExpression;
        }
        if (currentExpression == null) {
            return appendExpression;
        }
        if (currentExpression instanceof OrExpression) {
            return new AndExpression(new Parenthesis(currentExpression), appendExpression);
        } else {
            return new AndExpression(currentExpression, appendExpression);
        }
    }


    /**
     * 生成数据权限where条件
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月07日
     *
     * @author Japson Huang
     */
    protected Expression buildDataPermission(Table table) {
        Collection<PermissionData> columns = this.permissionHandler.getPermissionData(table.getName());
        if (columns == null || columns.size() == 0) {
            return null;
        }
        List<Expression> expressionList = new ArrayList<>();
        for (PermissionData data : columns) {
            expressionList.add(data.buildExpress(getAliasColumn(table, data.getColumn())));
        }
        return new MultiAndExpression(expressionList);
    }

    protected void processPlainSelect(PlainSelect plainSelect) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (this.permissionHandler.doTableFilter(fromTable.getName())) {
                return;
            }
            plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable));
        } else {
            processFromItem(fromItem);
        }

    }

    /**
     * 处理子查询等
     */
    protected void processFromItem(FromItem fromItem) {
        if (fromItem instanceof SubSelect) {
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

    @Override
    protected void processInsert(Insert insert, int index, String sql, Object obj) {
    }

    @Override
    protected void processDelete(Delete delete, int index, String sql, Object obj) {
        if (permissionHandler.doTableFilter(delete.getTable().getName())) {
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
        if (permissionHandler.doTableFilter(table.getName())) {
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
