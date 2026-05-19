package com.nledu.cloud.server.domain.query;

import com.nledu.cloud.server.domain.enmus.Sex;
import net.github.fastdev.mybatis.annotation.Operation;
import net.github.fastdev.mybatis.annotation.QueryField;

import java.util.List;

/**
 * <P><B>查询类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月02日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class UserQuery {

    @QueryField(condition= Operation.LIKE)
    private String username;

    private Sex sex;

    @QueryField(condition = Operation.IN,value = "id")
    private List<String> ids;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
