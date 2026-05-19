package com.nledu.cloud.server.service;

import com.nledu.cloud.server.domain.dto.UserDTO;
import com.nledu.cloud.server.domain.entity.UserEntity;
import com.nledu.cloud.server.domain.query.UserQuery;
import net.github.fastdev.common.web.BaseService4DTO;
import net.github.fastdev.mybatis.annotation.PageRequest;
import net.github.fastdev.mybatis.annotation.PageResponse;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface UserService extends BaseService4DTO<Long, UserEntity,UserDTO> {

    PageResponse<UserDTO> query(PageRequest<UserQuery> query);


    Integer customException();
}
