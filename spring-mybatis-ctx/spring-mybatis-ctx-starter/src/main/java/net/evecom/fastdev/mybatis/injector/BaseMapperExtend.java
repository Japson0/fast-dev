/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.mybatis.injector;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * <P><B>Mybatis-plus基础扩张类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface BaseMapperExtend<T> extends BaseMapper<T> {

    /**
     * 根据Id全量覆盖所有字段
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @author Japson Huang
     */
    int updateAllColumnById(@Param(Constants.ENTITY) T entity);

    /**
     * 批量插入
     * RevisionTrail:(Date/Author/Description)
     * 2021年08月28日
     *
     * @author Japson Huang
     */
    int insertBatch(@Param(Constants.LIST) Collection<? extends T> entries);

}
