package net.evecom.service.impl;

import net.evecom.fastdev.boot.template.BaseServiceImpl;
import net.evecom.fastdev.mybatis.injector.BaseMapperExtend;
import net.evecom.model.entity.DemoEntity;
import net.evecom.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月21日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Service
public class DemoServiceImpl extends BaseServiceImpl<Long, DemoEntity> implements DemoService {
    public DemoServiceImpl(BaseMapperExtend<DemoEntity> baseMapper) {
        super(baseMapper);
    }
}
