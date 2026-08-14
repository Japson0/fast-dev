package com.nlecloud.spring.annotation;

import com.nlecloud.spring.annotation.enums.Sex;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年05月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface UserInfo extends Serializable {

    Long getUserId();

    String getUsername();

    String getNickName();

    Long getTenantId();

    Long getSchoolId();

    Set<String> getRoles();


    String getEmail();


    Sex getSex();

    String getPhone();

    boolean isPhoneVerify();

    OrgInfo getOrgInfo();

    TenantInfo getTenantInfo();

    List<Long> getManagerOrges();

}
