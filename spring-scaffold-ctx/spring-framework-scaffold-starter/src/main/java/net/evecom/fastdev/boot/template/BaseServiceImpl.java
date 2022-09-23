/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.template;

import net.evecom.fastdev.common.web.BaseService;
import net.evecom.fastdev.mybatis.PageConditionDTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import net.evecom.fastdev.mybatis.annotation.PageConditionQuery;
import net.evecom.fastdev.mybatis.injector.BaseMapperExtend;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * <P><B>基本逻辑服务层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class BaseServiceImpl<ID extends Serializable, R extends BaseEntity<ID>> implements BaseService<ID, R> {

    /**
     * 基础DAO
     */
    private final BaseMapperExtend<R> baseMapper;


    public BaseServiceImpl(BaseMapperExtend<R> baseMapper) {
        this.baseMapper = baseMapper;
    }


    @Override
    public PageConditionQuery<R> getPage(PageConditionQuery<?> pageConditionDTO) {
        Assert.notNull(pageConditionDTO, "pageConditionDTO must not be null");
        PageConditionDTO<Object> page;
        if (pageConditionDTO instanceof PageConditionDTO) {
            page = (PageConditionDTO<Object>) pageConditionDTO;
        } else {
            page = new PageConditionDTO(pageConditionDTO);
        }
        return baseMapper.selectPage(page, page.buildQueryWrapper());
    }

    @Override
    public R getById(ID id) {
        Assert.notNull(id, "id must not be null");
        return baseMapper.selectById(id);
    }

    @Override
    @Transactional
    public int updateById(R t, boolean allColumns) {
        Assert.notNull(t, "object must not be null");
        Assert.notNull(t.getId(), "object.id must not be null");
        if (allColumns) {
            return baseMapper.updateAllColumnById(t);
        } else {
            return baseMapper.updateById(t);
        }
    }

    @Override
    @Transactional
    public int deleteById(ID id) {
        Assert.notNull(id, "id must not be null");
        return baseMapper.deleteById(id);
    }

    @Override
    @Transactional
    public int deleteById(List<ID> ids) {
        return baseMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional
    public int addById(R entity) {
        return baseMapper.insert(entity);
    }
}
