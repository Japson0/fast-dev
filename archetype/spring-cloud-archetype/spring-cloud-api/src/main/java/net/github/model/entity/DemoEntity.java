package net.github.model.entity;

import net.github.fastdev.mybatis.annotation.BaseEntity;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年08月17日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DemoEntity implements BaseEntity<Long> {
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long serializable) {

    }
}
