/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp;

import java.io.Serializable;

/**
 * 平台管理-租户信息表 实体
 *
 * @author Jay Jiang
 */
public class TenantInfo implements Serializable {
    /**
     * Id
     */
    private Long id;
    /**
     * Name
     */
    private String name;
    /**
     * Is valid
     */
    private Integer isValid;
    /**
     * Access key
     */
    private String accessKey;
    /**
     * Secret key
     */
    private String secretKey;

    /**
     * Gets id *
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id *
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name *
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name *
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets is valid *
     *
     * @return the is valid
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * Sets is valid *
     *
     * @param isValid is valid
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * Gets access key *
     *
     * @return the access key
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * Sets access key *
     *
     * @param accessKey access key
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * Gets secret key *
     *
     * @return the secret key
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Sets secret key *
     *
     * @param secretKey secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
