
/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.mybatis.config;

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
         * 密钥
         */
        private String sm4Key;

        public String getSm4Key() {
            return sm4Key;
        }

        public void setSm4Key(String sm4Key) {
            this.sm4Key = sm4Key;
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
