package net.evecom.controller;

import io.swagger.annotations.Api;
import net.evecom.fastdev.boot.template.BaseController;
import net.evecom.fastdev.common.web.BaseService;
import net.evecom.model.dto.DemoDTO;
import net.evecom.model.entity.DemoEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月21日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@RestController
@RequestMapping("demo")
@Api(tags = "测试")
public class DemoController extends BaseController<Long, DemoEntity> {


    public DemoController(BaseService<Long, DemoEntity> baseService) {
        super(baseService);
    }
}
