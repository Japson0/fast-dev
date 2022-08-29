/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.template;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.evecom.fastdev.mybatis.PageConditionDTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;

import java.io.Serializable;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface BaseService<ID extends Serializable, R extends BaseEntity<ID>> {

    /**
     * 查询分页数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @param pageConditionDTO 分页类
     * @author Japson Huang
     */
    <P> IPage<R> getPage(PageConditionDTO<P> pageConditionDTO);
    /**
     * 根据主键查询信息
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @param id 主键
     * @author Japson Huang
     */
    R getById(ID id);

    /**
     * 根据主键
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @param entity     实体类
     * @param allColumns 如果为true表示覆盖全部字段，如果为false则覆盖非空字段
     * @author Japson Huang
     */
    int updateById(R entity, boolean allColumns);

    /**
     * 根据主键删除实体类（可批量）
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @param id 主键
     * @author Japson Huang
     */
    int deleteById(ID id);

    /**
     * 根据主键新增数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月03日
     *
     * @param entity 实体类
     * @author Japson Huang
     */
    int addById(R entity);

}
