package com.nlecloud.spring.annotation.api;

import com.nlecloud.spring.annotation.UserInfoImpl;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年05月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface UserInfoService {

    UserInfoImpl getUserDetailById(String id);
}
