

package net.github.fastdev.common.web;

import net.github.fastdev.mybatis.annotation.BaseEntity;

import java.io.Serializable;

/**
 * <P><B>单表传输层:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年12月03日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface BaseService<ID extends Serializable, R extends BaseEntity<ID>> extends BaseService4DTO<ID, R, R> {


}
