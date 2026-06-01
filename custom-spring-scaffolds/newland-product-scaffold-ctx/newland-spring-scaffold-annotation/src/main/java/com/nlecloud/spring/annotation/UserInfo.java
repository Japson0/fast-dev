package com.nlecloud.spring.annotation;

import com.nlecloud.spring.annotation.enums.Sex;

import java.io.Serializable;
import java.util.*;

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

    Long getSchoolId();

    Long getTenantId();

    String getSchoolName();

    Collection<String> getRoles();

    Long getClassId();

    String getClassName();

    String getStudentNo();

    String getProfessionName();

    String getEmail();

    String getAvatar();

    Sex getSex();

    String getPhone();

    Set<Long> getAdminTenant();

    Long getOrgId();

    List<Long> getManagerOrges();

    Map<Long,List<Long>> getTenantOrg();
}
