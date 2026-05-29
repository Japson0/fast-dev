package com.nlecloud.spring.scaffold.handle;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.nlecloud.spring.scaffold.common.UserContext;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import org.springframework.util.CollectionUtils;

import java.util.List;
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

    private  final String columnName;

    private static final String NO_DATA_PERMISSION = "1 = 0";

    public OrgPermissionDataHandle(Predicate<String> ignoreTenantPredicate,String columnName) {
        this.ignoreTenantPredicate = ignoreTenantPredicate;
        this.columnName=columnName;
    }

    @Override
    public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
        if(ignoreTenantPredicate.test(table.getName())||UserContext.isTenantAdmin()){
            return null;
        }

        List<Long> dataOrg = UserContext.getUserInfo().getManagerOrges();
        if (CollectionUtils.isEmpty(dataOrg)) {
            return parseCondition(NO_DATA_PERMISSION);
        }
        String tableName = table.getAlias() == null ? table.getFullyQualifiedName() : table.getAlias().getName();
        String orgIds = dataOrg.stream().map(String::valueOf).collect(Collectors.joining(","));
        return parseCondition(tableName + "." + columnName + " IN (" + orgIds + ")");
    }

    private Expression parseCondition(String sqlSegment) {
        try {
            return CCJSqlParserUtil.parseCondExpression(sqlSegment);
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }
}
