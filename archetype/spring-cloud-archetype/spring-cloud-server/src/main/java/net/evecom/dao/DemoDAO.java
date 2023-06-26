package net.evecom.dao;

import net.evecom.fastdev.mybatis.injector.BaseMapperExtend;
import net.evecom.model.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月21日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Mapper
public interface DemoDAO extends BaseMapperExtend<DemoEntity> {
}
