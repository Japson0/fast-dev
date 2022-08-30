/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.template;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.evecom.fastdev.common.annotation.Insert;
import net.evecom.fastdev.common.annotation.Update;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.mybatis.PageConditionDTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

/**
 * <P><B>公用控制层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class BaseController<ID extends Serializable, T extends BaseEntity<ID>> implements BaseControllerInterface<ID, T> {

    /**
     * 逻辑层
     */
    protected final BaseService<ID, T> baseService;

    public BaseController(BaseService<ID, T> baseService) {
        this.baseService = baseService;
    }

    /**
     * 更新操作，全覆盖
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    public RestResponse update(@Validated(Update.class) @RequestBody T entity) {

        if (entity.getId() == null) {
            throw new IllegalArgumentException("id must be not null");
        }
        return RestResponse.UD4ResCount((baseService.updateById(entity, true)));
    }

    /**
     * 新增操作
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    public RestResponse insert(@Validated(Insert.class) @RequestBody T entity) {
        baseService.addById(entity);
        return RestResponse.renderSuccess(entity.getId());
    }

    /**
     * 删除操作
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    public RestResponse delete(@PathVariable ID id) {
        return RestResponse.UD4ResCount(baseService.deleteById(id));
    }

    /**
     * 获取明细
     * RevisionTrail:(Date/Author/Description)
     * 2022年02月08日
     *
     * @author Japson Huang
     */
    @Override
    public RestResponse<T> get(ID id) {
        return RestResponse.renderSuccess(baseService.getById(id));
    }

    /**
     * 分页查询
     *
     * @param conditionDTO
     * @param <P>
     * @return
     */
    @Override
    public <P> RestResponse<IPage<T>> getPage(PageConditionDTO<P> conditionDTO) {
        return RestResponse.renderSuccess(baseService.getPage(conditionDTO));
    }

}