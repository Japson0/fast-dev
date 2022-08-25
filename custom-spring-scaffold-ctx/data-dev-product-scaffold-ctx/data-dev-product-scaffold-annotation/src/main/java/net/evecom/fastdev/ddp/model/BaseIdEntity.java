package net.evecom.fastdev.ddp.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import net.evecom.fastdev.mybatis.annotation.BaseEntity;

/**
 * <P><B>只包含ID的实体类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class BaseIdEntity implements BaseEntity<Long> {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
