package com.nledu.cloud.server.domain.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nlecloud.spring.scaffold.common.BaseEntity;
import com.nledu.cloud.server.domain.enmus.Sex;
import net.github.fastdev.common.annotation.Dictionary;
import net.github.fastdev.common.annotation.Insert;
import net.github.fastdev.common.annotation.Update;
import net.github.fastdev.common.annotation.WebSecuritySerialize;
import net.github.fastdev.common.model.DistortionType;
import net.github.fastdev.mybatis.annotation.CryptAble;
import net.github.fastdev.mybatis.annotation.Encrypt;
import net.github.fastdev.mybatis.annotation.EncryptType;

import javax.validation.constraints.NotNull;

/**
 * <P><B>用户信息:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@TableName("userinfo")
public class UserEntity extends BaseEntity implements CryptAble {


    @NotNull(message = "不允许为空",groups = {Update.class, Insert.class})
    private Sex sex;

    private String username;

    @WebSecuritySerialize(distortion= DistortionType.MOBILE_PHONE)
    @Encrypt(EncryptType.SM4)
    private String phone;

    @Encrypt(EncryptType.SM3)
    @TableLogic
    private String password;


    @Dictionary("type")
    private String dictionName;


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDictionName() {
        return dictionName;
    }

    public void setDictionName(String dictionName) {
        this.dictionName = dictionName;
    }
}
