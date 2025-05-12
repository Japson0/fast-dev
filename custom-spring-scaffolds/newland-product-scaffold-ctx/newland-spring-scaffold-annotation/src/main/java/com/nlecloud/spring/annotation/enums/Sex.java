package com.nlecloud.spring.annotation.enums;

import net.github.fastdev.common.model.ComEnum;

/**
 * <P><B>性别:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年05月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum Sex implements ComEnum<Integer> {

    MALE("男",1),FEMALE("女",0);
       /**
            * 显示值
            */
           private final String display;

           /**
            * 实际值
            */
           private final Integer value;

           Sex(String display, Integer value) {
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
