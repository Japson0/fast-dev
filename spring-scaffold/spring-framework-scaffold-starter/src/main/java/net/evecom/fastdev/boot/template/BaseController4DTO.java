package net.evecom.fastdev.boot.template;

import io.swagger.annotations.ApiOperation;
import net.evecom.fastdev.common.annotation.Insert;
import net.evecom.fastdev.common.annotation.Update;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.common.web.BaseService4DTO;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <P><B>传输类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年11月24日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class BaseController4DTO<ID extends Serializable, T extends BaseEntity<ID>, DTO extends T> {

    /**
     * 逻辑层
     */
    protected final BaseService4DTO<ID, T, DTO> baseService;

    public BaseController4DTO(BaseService4DTO<ID, T, DTO> baseService) {
        this.baseService = baseService;
    }

    /**
     * 更新操作，全覆盖
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    @PutMapping
    @ApiOperation("更新")
    public RestResponse update(@Validated(Update.class) @RequestBody DTO entity) {

        if (entity.getId() == null) {
            throw new IllegalArgumentException("id must be not null");
        }
        int i = baseService.updateById(entity, true);
        if (i > 0) {
            return RestResponse.renderSuccess2Msg("更新成功").setData(entity.getId());
        } else {
            return RestResponse.renderError("更新失败，不存在对应的数据");
        }
    }

    /**
     * 新增操作
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    @PostMapping
    @ApiOperation("新增")
    public RestResponse insert(@Validated(Insert.class) @RequestBody DTO entity) {
        if (baseService.addById(entity) > 0) {
            return RestResponse.renderSuccess(entity.getId()).setMessage("新增成功");
        } else {
            return RestResponse.renderError("新增失败");
        }
    }

    /**
     * 删除操作
     * RevisionTrail:(Date/Author/Description)
     * 2021年07月26日
     *
     * @author Japson Huang
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public RestResponse delete(@PathVariable ID id) {
        if (baseService.deleteById(id) > 0) {
            return RestResponse.renderSuccess(id).setMessage("删除成功");
        } else {
            return RestResponse.renderError("删除失败，不存在对应的数据");
        }
    }

    /**
     * 获取明细
     * RevisionTrail:(Date/Author/Description)
     * 2022年02月08日
     *
     * @author Japson Huang
     */
    @GetMapping("{id}")
    @ApiOperation("查询")
    public RestResponse<T> get(@PathVariable ID id) {
        return RestResponse.renderSuccess(baseService.getById(id));
    }


}
