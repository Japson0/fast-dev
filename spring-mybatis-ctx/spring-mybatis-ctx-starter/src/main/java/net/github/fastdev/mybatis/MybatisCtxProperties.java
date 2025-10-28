


package net.github.fastdev.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>Mybatis-ctx配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月13日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@ConfigurationProperties(prefix = "mybatis-plus")
public class MybatisCtxProperties {

    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * 密钥信息
     */
    private Encrcypt encrcypt;

    /**
     * 密钥信息类
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月23日
     *
     * @author Japson Huang
     */
    public static class Encrcypt {

        /**
         * 是否开启
         */
        private boolean enable=false;
        /**
         * 密钥
         */
        private String sm4Key;

        private String sm3Key;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getSm4Key() {
            return sm4Key;
        }

        public void setSm4Key(String sm4Key) {
            this.sm4Key = sm4Key;
        }

        public String getSm3Key() {
            return sm3Key;
        }

        public void setSm3Key(String sm3Key) {
            this.sm3Key = sm3Key;
        }
    }

    public Encrcypt getEncrcypt() {
        return encrcypt;
    }

    public void setEncrcypt(Encrcypt encrcypt) {
        this.encrcypt = encrcypt;
    }

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }
}
