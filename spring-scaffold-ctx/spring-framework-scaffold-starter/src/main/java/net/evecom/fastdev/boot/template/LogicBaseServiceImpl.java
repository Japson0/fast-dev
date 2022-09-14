/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.template;

import cn.hutool.core.util.TypeUtil;
import net.evecom.fastdev.common.exception.ResourceException;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import net.evecom.fastdev.mybatis.injector.BaseMapperExtend;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * <P><B>基本服务层:</B></P>
 * 删除时逻辑删除
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class LogicBaseServiceImpl<ID extends Serializable, R extends BaseEntity<ID>> extends BaseServiceImpl<ID, R> {

    /**
     * 基础DAO
     */
    private final BaseMapperExtend<R> baseMapper;

    /**
     * 泛型类
     */
    private final Class<R> clazz;


    public LogicBaseServiceImpl(BaseMapperExtend<R> baseMapper) {
        super(baseMapper);
        this.baseMapper = baseMapper;
        clazz = (Class<R>) TypeUtil.getTypeArgument(this.getClass(), 1);
    }

    /**
     * 逻辑删除
     * RevisionTrail:(Date/Author/Description)
     * 2021年12月03日
     *
     * @author Japson Huang
     */
    @Override
    @Transactional
    public int deleteById(ID id) {
        Assert.notNull(id, "id must not be null");
        try {
            R baseEntity = clazz.newInstance();
            baseEntity.setId(id);
            return baseMapper.deleteById(baseEntity);
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public int addById(R entity) {
        return baseMapper.insert(entity);
    }
}
