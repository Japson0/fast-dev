
/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.incrementer.*;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.evecom.fastdev.mybatis.config.MybatisCtxProperties;
import net.evecom.fastdev.mybatis.encrypt.EncryptCertificate;
import net.evecom.fastdev.mybatis.encrypt.SkinEncrypt;
import net.evecom.fastdev.mybatis.encrypt.SkinMethodPredicate;
import net.evecom.fastdev.mybatis.injector.method.InsertBatch;
import net.evecom.fastdev.mybatis.injector.method.UpdateAllColumnById;
import net.evecom.fastdev.mybatis.sqlparser.DecryptResultSetInterceptor;
import net.evecom.fastdev.mybatis.sqlparser.EncryptParamInterceptor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;


/**
 * <P><B>Mybatis配置类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月21日 C    REATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(MybatisCtxProperties.class)
public class MyBatisPlusConfig {

    /**
     * mybatis额外配置
     */
    @Autowired
    private MybatisCtxProperties mybatisCtxProperties;

    @Bean
    @Order(10)
    public MybatisPlusInterceptor mybatisPlusInterceptor(ObjectProvider<List<InnerInterceptor>> InnerInterceptors) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        List<InnerInterceptor> innerInterceptors = InnerInterceptors.getIfAvailable();
        if (innerInterceptors != null) {
            for (InnerInterceptor innerInterceptor : innerInterceptors) {
                interceptor.addInnerInterceptor(innerInterceptor);
            }
        }
        BaseQuery.initDbType(mybatisCtxProperties.getDbType());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(mybatisCtxProperties.getDbType()));
        return interceptor;
    }


    @Bean
    @ConditionalOnExpression("#{environment['mybatis-plus.encrcypt.sm4-key']!=null}")
    public EncryptCertificate encryptCertificate(MybatisCtxProperties mybaitsCtxProperties) {
        return new EncryptCertificate(mybaitsCtxProperties.getEncrcypt());
    }

    /**
     * 加密拦截器
     * RevisionTrail:(Date/Author/Description)
     * 2020年10月13日
     *
     * @author Japson Huang
     */
    @Bean
    @Order(11)
    @ConditionalOnBean(EncryptCertificate.class)
    public Interceptor encryptParamParser(EncryptCertificate encryptCertificate, SkinMethodPredicate skinMethodPredicate) {
        return new EncryptParamInterceptor(encryptCertificate,skinMethodPredicate);
    }


    @Bean
    public SkinMethodPredicate skinMethodPredicate(){
        return new SkinMethodPredicate();
    }
    /**
     * 解密拦截器
     *
     * @return
     */
    @Bean
    @ConditionalOnBean(EncryptCertificate.class)
    public DecryptResultSetInterceptor encryptResultSetHandle(EncryptCertificate encryptCertificate) {
        return new DecryptResultSetInterceptor(encryptCertificate);
    }

    /**
     * 可自定义注入，下面的是逻辑删除注入，根据项目需要判断是否需要
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @author Japson Huang
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
                methodList.add(new UpdateAllColumnById());
                methodList.add(new InsertBatch());
                return methodList;
            }
        };
    }

    /**
     * 租户插件
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @author Japson Huang
     */
    @Bean
    @ConditionalOnBean(TenantLineHandler.class)
    public InnerInterceptor tenantLineInnerInterceptor(TenantLineHandler tenantLineHandler) {
        return new TenantLineInnerInterceptor(tenantLineHandler);
    }

    @Bean
    @ConditionalOnProperty(prefix = "mybatis-plus", name = "db-type")
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        DbType dbType = mybatisCtxProperties.getDbType();
        IKeyGenerator iKeyGenerator = null;
        if (dbType == DbType.ORACLE || dbType == DbType.ORACLE_12C) {
            iKeyGenerator = new OracleKeyGenerator();
        } else if (dbType == DbType.DB2) {
            iKeyGenerator = new DB2KeyGenerator();
        } else if (dbType == DbType.H2) {
            iKeyGenerator = new H2KeyGenerator();
        } else if (dbType == DbType.KINGBASE_ES) {
            iKeyGenerator = new KingbaseKeyGenerator();
        } else if (dbType == DbType.POSTGRE_SQL) {
            iKeyGenerator = new PostgreKeyGenerator();
        } else if (dbType == DbType.DM) {
            iKeyGenerator = new DmKeyGenerator();
        }
        if (iKeyGenerator != null) {
            List<IKeyGenerator> keyGenerators = Collections.singletonList(iKeyGenerator);
            return plusProperties -> plusProperties.getGlobalConfig().getDbConfig().setKeyGenerators(keyGenerators);

        }
        return plusProperties -> plusProperties.getGlobalConfig().getDbConfig();
    }
}
