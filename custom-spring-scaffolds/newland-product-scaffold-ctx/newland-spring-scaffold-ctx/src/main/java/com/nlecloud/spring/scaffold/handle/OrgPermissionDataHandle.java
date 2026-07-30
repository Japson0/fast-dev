package com.nlecloud.spring.scaffold.handle;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.nlecloud.spring.scaffold.common.UserContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <P><B>机构数据权限:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年05月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class OrgPermissionDataHandle implements MultiDataPermissionHandler {

    private Predicate<String> ignoreTenantPredicate;

    private final String orgColumn;

    private final String createPersonColumn;

    public OrgPermissionDataHandle(Predicate<String> ignoreTenantPredicate, String orgColumn, String createPersonColumn) {
        this.ignoreTenantPredicate = ignoreTenantPredicate;
        this.orgColumn = orgColumn;
        this.createPersonColumn = createPersonColumn;
    }

    @Override
    public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
        if (ignoreTenantPredicate.test(table.getName()) || UserContext.isTenantAdmin()) {
            return null;
        }

        Collection<Long> dataOrg = UserContext.getUserInfo().getManagerOrges();
        if (CollectionUtils.isEmpty(dataOrg)) {
            return new EqualsTo(new Column(table,createPersonColumn), new LongValue(UserContext.getUserId()));
        }

        return new InExpression(new Column(table,orgColumn),new ExpressionList(dataOrg.stream().map(LongValue::new).collect(Collectors.toList())));
    }

}
