
package com.nlecloud.spring.scaffold.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.TableColumn;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;


/**
 * <P><B>公用字段填充:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月20日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class AutoMetaObjectHandle implements MetaObjectHandler {


    /**
     * 在进行插入操作的时候会进行填充操作
     * 如调用mybatis-plus的save()操作或者XML里面是<insert></insert>标签的时候。
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @author Japson Huang
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Timestamp now = new Timestamp(SystemClock.now());
        if (canSet(TableColumn.CREATE_TIME_NAME, metaObject)) {
            initValue(metaObject, TableColumn.CREATE_TIME_NAME, now);
        }
        if (canSet(TableColumn.CREATE_USER_ID_NAME, metaObject)) {
            initValue(metaObject, TableColumn.CREATE_USER_ID_NAME, UserContext.getUserId());
        }
        updateFill(metaObject);
    }


    /**
     * 同插入操作。但在XML里面是<update></update>的时候会执行
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @author Japson Huang
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (canSet(TableColumn.UPDATE_TIME_NAME, metaObject)) {
            initValue(metaObject, TableColumn.UPDATE_TIME_NAME, new Timestamp(SystemClock.now()));
        }
        if (canSet(TableColumn.UPDATE_USER_ID_NAME, metaObject)) {
            initValue(metaObject, TableColumn.UPDATE_USER_ID_NAME, UserContext.getUserId());
        }
    }


    public boolean canSet(String fieldName, MetaObject metaObject) {

        return metaObject.hasGetter(fieldName) && metaObject.getValue(fieldName) == null;
    }

    /**
     * 根据字段初始化实体类里面的值
     * RevisionTrail:(Date/Author/Description)
     * 2020年07月02日
     *
     * @author Japson Huang
     */
    private void initValue(MetaObject metaObject, String fieldName, Object value) {
        metaObject.setValue(fieldName, value);
    }
}
