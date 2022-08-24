/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.cache.redis;

/**
 * <P><B>缓存变量类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年06月20日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class RedisTime {

    /**
     * 代表一天后过期
     */
    public final static String ONE_DAY = "ONE_DAY";

    /**
     * 代表一周后过期
     */
    public final static String ONE_WEEK = "ONE_WEEK";
    /**
     * 代表一月后过期
     */
    public final static String ONE_MONTH = "ONE_MONTH";
    /**
     * 代表半年后过期
     */
    public final static String HALF_YEAR = "HALF_YEAR";
    /**
     * 代表一年后过期
     */
    public final static String ONE_YEAR = "ONE_YEAR";

    /**
     * 字典码
     */
    public final static String DICTIONARY = "DICTIONARY";
}
