package com.nlecloud.spring.scaffold.handle;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.nlecloud.spring.scaffold.common.UserContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class TenantHandle implements TenantLineHandler {

    private Predicate<String> ignoreTenantPredicate;

    public TenantHandle(Predicate<String> ignoreTenantPredicate) {
        this.ignoreTenantPredicate = ignoreTenantPredicate;
    }

    @Override
    public Expression getTenantId() {
        return new LongValue(UserContext.getUserInfo().getTenantId());
    }


    @Override
    public boolean ignoreTable(String tableName) {
        return ignoreTenantPredicate.test(tableName);
    }
}
