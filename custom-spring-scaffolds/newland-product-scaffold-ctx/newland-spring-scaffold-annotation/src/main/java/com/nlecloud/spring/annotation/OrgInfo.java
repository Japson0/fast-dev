package com.nlecloud.spring.annotation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * <P><B>机构信息:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2026年07月29日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Data
public class OrgInfo implements Serializable {

    /**
     * 序列化号
     */
    private static final long serialVersionUID = -1910642529275144840L;

    private Long id;

    private String name;

    private boolean admin;
}
