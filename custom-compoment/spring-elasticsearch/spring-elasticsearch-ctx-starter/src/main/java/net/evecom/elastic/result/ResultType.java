package net.evecom.elastic.result;

import net.evecom.elastic.pojo.EsBaseEntity;

import java.util.Map;

/**
 * <P><B>结果类型:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum ResultType {

    /**
     * Map类型
     */
    MAP,
    /**
     * ES实体类
     */
    ENTITY,
    /**
     * 普通类型
     */
    COMMON;

    public static ResultType getType(Class clazz){
        if(clazz.isAssignableFrom(Map.class)){
            return MAP;
        }else if(clazz.isAssignableFrom(EsBaseEntity.class)){
            return ENTITY;
        }else{
            return COMMON;
        }
    }
}
