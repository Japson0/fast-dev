package net.evecom.fastdev.ddp.handle;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.evecom.fastdev.common.exception.NoUserInfoException;
import net.evecom.fastdev.ddp.UserContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * <P><B租户插件:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月25日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DataDevTenantHandler implements TenantLineHandler {

    /**
     * 列表信息
     */
    public final Collection<String> ignoreTable;

    public DataDevTenantHandler(String[] ignoreTable) {
        if (ignoreTable == null) {
            this.ignoreTable = Collections.emptyList();
        } else {
            this.ignoreTable = new HashSet<>(Arrays.asList(ignoreTable));
        }
    }

    @Override
    public Expression getTenantId() {
        Long tenantId = UserContext.getTenantId();
        if (tenantId == null) {
            throw NoUserInfoException.getInstance();
        }
        return new LongValue(tenantId);
    }

    @Override
    public boolean ignoreTable(String tableName) {
        //超管忽略
        if (UserContext.isAdmin()) {
            return false;
        }
        return ignoreTable.contains(tableName);
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }
}
