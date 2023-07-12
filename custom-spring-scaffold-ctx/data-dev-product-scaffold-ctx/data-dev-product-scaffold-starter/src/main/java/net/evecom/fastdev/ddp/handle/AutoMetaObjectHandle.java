/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/*
 *Copyright(c)2005,2018,EVECOMTechnologyCo.,Ltd.Allrightsreserved.
 *EVECOMPROPRIETARY/CONFIDENTIAL.Useissubjecttolicenseterms.
 *
 */
package net.evecom.fastdev.ddp.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import net.evecom.fastdev.ddp.UserContext;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

import static net.evecom.fastdev.ddp.enums.TableColumnName.*;

/**
 * <P><B>公用字段填充:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2019年05月20日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class AutoMetaObjectHandle implements MetaObjectHandler {


    public AutoMetaObjectHandle() {
    }

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
        if (canSet(TIME_CREATED_FIELD, metaObject)) {
            initValue(metaObject, TIME_CREATED_FIELD, now);
        }
        if (canSet(CREATOR_ID_FIELD, metaObject)) {
            initValue(metaObject, CREATOR_ID_FIELD, UserContext.getUserId());
        }
        if (canSet(CREATOR_NAME_FIELD, metaObject)) {
            initValue(metaObject, CREATOR_NAME_FIELD, UserContext.getUserName());
        }
        if (canSet(TIME_MODIFIED_FIELD, metaObject)) {
            initValue(metaObject, TIME_MODIFIED_FIELD, new Timestamp(SystemClock.now()));
        }
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
        if (canSet(TIME_MODIFIED_FIELD, metaObject)) {
            initValue(metaObject, TIME_MODIFIED_FIELD, new Timestamp(SystemClock.now()));
        }
        if (canSet(MODIFIER_ID_FIELD, metaObject)) {
            initValue(metaObject, MODIFIER_ID_FIELD, UserContext.getUserId());
        }
        if (canSet(MODIFIER_NAME_FIELD, metaObject)) {
            initValue(metaObject, MODIFIER_NAME_FIELD, UserContext.getUserName());
        }

    }


    public boolean canSet(String fieldName, MetaObject metaObject) {

        return metaObject.hasGetter(fieldName) && metaObject.getValue(fieldName) == null && metaObject.getValue(fieldName) == null;
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
