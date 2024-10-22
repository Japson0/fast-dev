package net.evecom.fastdev.ddp.handle;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.evecom.fastdev.common.exception.NoUserInfoException;
import net.evecom.fastdev.ddp.UserContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.binding.MapperRegistry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * <P><B租户插件:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月25日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class DataDevTenantHandler implements TenantLineHandler {

    /**
     * 列表信息
     */
    public final Collection<String> ignoreTable;


    private MapperRegistry mapperRegistry;

    /**
     * 动态忽略租户
     */
    public static final ThreadLocal<Boolean> IGNORE_LOCAL = ThreadLocal.withInitial(() -> false);

    public DataDevTenantHandler(String[] ignoreTable) {
        if (ignoreTable == null) {
            this.ignoreTable = Collections.emptyList();
        } else {
            this.ignoreTable = new HashSet<>(ignoreTable.length);
            for (String s : ignoreTable) {
                this.ignoreTable.add(s.toUpperCase());
            }
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
        //如果租户为空，则忽略，这种情况主要是出现在定时任务调用，获取数据的接口比如根据ID获取明细
        if (UserContext.getTenantId() == null) {
            return true;
        }
        if (IGNORE_LOCAL.get()) {
            return true;
            //超管忽略
        } else if (UserContext.isAdmin()) {
            return true;
        }
        return ignoreTable.contains(tableName.toUpperCase());
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

}
