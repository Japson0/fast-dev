package com.nledu.cloud.server.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.nledu.cloud.server.domain.dto.UserDTO;
import com.nledu.cloud.server.domain.entity.UserEntity;
import net.github.fastdev.mybatis.injector.BaseMapperExtend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Mapper
public interface UserMapper extends BaseMapperExtend<UserEntity> {


    IPage<UserDTO> customPage(IPage page, @Param(Constants.WRAPPER) QueryWrapper<UserEntity> wrapper);
}
