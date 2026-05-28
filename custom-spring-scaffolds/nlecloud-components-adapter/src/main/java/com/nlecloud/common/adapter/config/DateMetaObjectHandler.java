package com.nlecloud.common.adapter.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.nlecloud.common.constant.SecurityConstants;
import com.nlecloud.spring.scaffold.common.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 自定义填充公共字段
 * @author warrior
 */
@Component
public class DateMetaObjectHandler implements MetaObjectHandler {
    private MybatisPlusAutoFillProperties autoFillProperties=new MybatisPlusAutoFillProperties();

    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }

    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(autoFillProperties.getCreateTimeField(), metaObject);
        Object updateTime = getFieldValByName(autoFillProperties.getUpdateTimeField(), metaObject);
        Object createPerson = getFieldValByName(autoFillProperties.getCreatePersonField(), metaObject);
        Object updatePerson = getFieldValByName(autoFillProperties.getUpdatePersonField(), metaObject);
        if (createTime == null || updateTime == null) {
            Date date = new Date();
            if (createTime == null) {
                setFieldValByName(autoFillProperties.getCreateTimeField(), date, metaObject);
            }
            if (updateTime == null) {
                setFieldValByName(autoFillProperties.getUpdateTimeField(), date, metaObject);
            }
        }
        if(createPerson == null || updatePerson == null){
            if(createPerson == null){
                setFieldValByName(autoFillProperties.getCreatePersonField(), getUserId(), metaObject);
            }
            if(updatePerson == null){
                setFieldValByName(autoFillProperties.getUpdatePersonField(), getUserId(), metaObject);
            }
        }
    }

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(autoFillProperties.getUpdateTimeField(), new Date(), metaObject);
        setFieldValByName(autoFillProperties.getUpdatePersonField(), getUserId(), metaObject);

    }

    private String getUserId(){
        Long userId = UserContext.getUserId();

        return String.valueOf(userId);
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if(servletRequestAttributes == null){
//            return "";
//        }
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
//        return userId;
    }

}
