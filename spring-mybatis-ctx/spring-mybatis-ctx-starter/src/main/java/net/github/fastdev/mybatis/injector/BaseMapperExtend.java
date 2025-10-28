
package net.github.fastdev.mybatis.injector;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import net.github.fastdev.mybatis.encrypt.SkinEncrypt;
import org.apache.ibatis.annotations.Param;

/**
 * <P><B>Mybatis-plus基础扩张类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年07月02日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public interface BaseMapperExtend<T> extends BaseMapper<T> {

    /**
     * 根据Id全量覆盖所有字段
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @author Japson Huang
     */
    int updateAllColumnById(@Param(Constants.ENTITY) T entity);

    /**
     *获取重复字段的数量
     *RevisionTrail:(Date/Author/Description)
     * 2025年05月19日
     *@author Japson Huang
     *
    */
    @SkinEncrypt
    Long selectRepetitionCount(@Param(Constants.ENTITY) T entity);
}
