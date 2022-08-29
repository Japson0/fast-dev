package net.evecom.fastdev.ddp.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

import static net.evecom.fastdev.ddp.enums.TableColumnName.*;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public abstract class BaseEntity<T extends Serializable> implements net.evecom.fastdev.mybatis.annotation.BaseEntity<T> {

}
