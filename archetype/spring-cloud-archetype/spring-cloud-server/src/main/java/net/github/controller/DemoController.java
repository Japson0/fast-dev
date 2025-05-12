package net.github.controller;

import io.swagger.annotations.Api;
import net.github.fastdev.boot.template.BaseController;
import net.github.fastdev.common.web.BaseService;
import net.github.model.entity.DemoObjEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月21日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@RestController
@RequestMapping("demo")
@Api(tags = "测试")
public class DemoController extends BaseController<Long, DemoObjEntity> {


    public DemoController(BaseService<Long, DemoObjEntity> baseService) {
        super(baseService);
    }
}
