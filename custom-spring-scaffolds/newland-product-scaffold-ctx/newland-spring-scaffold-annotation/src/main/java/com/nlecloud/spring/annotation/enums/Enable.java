package com.nlecloud.spring.annotation.enums;

import net.github.fastdev.common.model.ComEnum;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年04月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum Enable implements ComEnum<Integer> {

    ENABLE("可用",1),UN_ENABLE("不可用",0);
       /**
            * 显示值
            */
           private final String display;
       
           /**
            * 实际值
            */
           private final Integer value;

    Enable(String display, Integer value) {
               this.display = display;
               this.value = value;
           }
       
           @Override
           public String getDisplay() {
               return display;
           }
       
           @Override
           public Integer getValue() {
               return value;
           }
}
