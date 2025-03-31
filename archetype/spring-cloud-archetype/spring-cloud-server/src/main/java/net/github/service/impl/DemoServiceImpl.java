package net.github.service.impl;

import net.github.fastdev.boot.template.BaseServiceImpl;
import net.github.fastdev.mybatis.injector.BaseMapperExtend;
import net.github.model.entity.DemoEntity;
import net.github.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月21日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Service
public class DemoServiceImpl extends BaseServiceImpl<Long, DemoEntity> implements DemoService {
    public DemoServiceImpl(BaseMapperExtend<DemoEntity> baseMapper) {
        super(baseMapper);
    }
}
