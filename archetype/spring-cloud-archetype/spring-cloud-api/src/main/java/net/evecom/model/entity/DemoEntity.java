package net.evecom.model.entity;

import net.evecom.fastdev.ddp.model.AbstractEntity;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;

import java.io.Serializable;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年08月17日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class DemoEntity extends AbstractEntity<Long> {
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long serializable) {

    }
}
