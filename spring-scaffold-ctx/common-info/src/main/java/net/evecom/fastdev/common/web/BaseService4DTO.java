package net.evecom.fastdev.common.web;

import net.evecom.fastdev.mybatis.annotation.BaseEntity;
import net.evecom.fastdev.mybatis.annotation.PageConditionQuery;

import java.io.Serializable;
import java.util.List;

/**
 * <P><B>复杂传输层基础类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年11月24日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface BaseService4DTO<ID extends Serializable, R extends BaseEntity<ID>, DTO extends R> {

    /**
     * 查询分页数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @param pageConditionDTO 分页类
     * @author Japson Huang
     */
    PageConditionQuery<DTO> getPage(PageConditionQuery<?> pageConditionDTO);

    /**
     * 新增或更新
     * 如果表不存在，则新增，存在则更新
     * RevisionTrail:(Date/Author/Description)
     * 2023年07月18日
     *
     * @author Japson Huang
     */
    int addOrUpdate(DTO entity);

    /**
     * 新增或更新
     * 如果表不存在，则新增，存在则更新
     * RevisionTrail:(Date/Author/Description)
     * 2023年07月18日
     *
     * @param allColumns 是否全覆盖
     * @author Japson Huang
     */
    int addOrUpdate(DTO entity, boolean allColumns);

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
    int updateById(DTO entity, boolean allColumns);

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
     * 批量删除
     * RevisionTrail:(Date/Author/Description)
     * 2022年09月19日
     *
     * @author Japson Huang
     */
    int deleteById(List<ID> ids);

    /**
     * 根据主键新增数据
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月03日
     *
     * @param entity 实体类
     * @author Japson Huang
     */
    int addById(DTO entity);
}
