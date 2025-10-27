package net.github.fastdev.mybatis.config;

import net.github.fastdev.mybatis.MybatisCtxProperties;
import net.github.fastdev.mybatis.encrypt.EncryptCertificate;
import net.github.fastdev.mybatis.encrypt.SkinMethodPredicate;
import net.github.fastdev.mybatis.sqlparser.DecryptResultSetInterceptor;
import net.github.fastdev.mybatis.sqlparser.EncryptParamInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年10月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
@ConditionalOnProperty(value = "mybatis-plus.encrcypt.enable",havingValue = "true")
public class EncryptConfig  {
    /**
     * 解密拦截器
     *
     * @return
     */
    @Bean
    public DecryptResultSetInterceptor encryptResultSetHandle(EncryptCertificate encryptCertificate) {
        return new DecryptResultSetInterceptor(encryptCertificate);
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
    public Interceptor encryptParamParser(EncryptCertificate encryptCertificate, SkinMethodPredicate skinMethodPredicate) {
        return new EncryptParamInterceptor(encryptCertificate,skinMethodPredicate);
    }



    @Bean
    public EncryptCertificate encryptCertificate(MybatisCtxProperties mybaitsCtxProperties) {
        return new EncryptCertificate(mybaitsCtxProperties.getEncrcypt());
    }
}
