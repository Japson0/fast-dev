

package net.github.fastdev.boot.template;

import net.github.fastdev.common.web.BaseService;
import net.github.fastdev.mybatis.annotation.BaseObjEntity;

import java.io.Serializable;

/**
 * <P><B>公用控制层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class BaseController<ID extends Serializable, T extends BaseObjEntity<ID>> extends BaseController4DTO<ID, T, T> {

    public BaseController(BaseService<ID, T> baseService) {
        super(baseService);
    }

}
