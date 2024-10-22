package net.evecom.service;

import net.evecom.api.ExportDubboService;
import net.evecom.fastdev.common.web.BaseService;
import net.evecom.fastdev.common.web.BaseService4DTO;
import net.evecom.model.entity.DemoEntity;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月21日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface DemoService extends ExportDubboService, BaseService<Long, DemoEntity> {
}
