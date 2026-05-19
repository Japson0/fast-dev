package com.nledu.cloud.server.service.impl;

import com.nlecloud.spring.common.exception.BusinessException;
import com.nledu.cloud.server.dao.UserMapper;
import com.nledu.cloud.server.domain.dto.UserDTO;
import com.nledu.cloud.server.domain.entity.UserEntity;
import com.nledu.cloud.server.domain.query.UserQuery;
import com.nledu.cloud.server.service.UserService;
import net.github.fastdev.boot.template.BaseService4DTOImpl;
import net.github.fastdev.common.exception.CommonError;
import net.github.fastdev.mybatis.annotation.PageRequest;
import net.github.fastdev.mybatis.annotation.PageResponse;
import net.github.fastdev.mybatis.util.PageWrapper;
import org.springframework.stereotype.Service;

/**
 * <P><B>用户信息逻辑层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Service
public class UserServiceImpl extends BaseService4DTOImpl<Long, UserEntity,UserDTO> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper baseMapper) {
        super(baseMapper);
        this.userMapper=baseMapper;
    }

    @Override
    public PageResponse<UserDTO> query(PageRequest<UserQuery> query) {
        PageWrapper pageWrapper = new PageWrapper(query);
        userMapper.customPage(pageWrapper,pageWrapper.buildQueryWrapper());
        return pageWrapper.getPageResponse();
    }

    @Override
    public Integer customException() {
        throw new BusinessException(CommonError.USER_ACCOUNT_DOES_NOT_EXIST.getCode());
    }

}
