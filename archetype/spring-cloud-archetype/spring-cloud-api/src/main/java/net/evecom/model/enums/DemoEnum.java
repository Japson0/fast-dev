package net.evecom.model.enums;

import net.evecom.fastdev.common.model.ComEnum;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月21日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public enum DemoEnum implements ComEnum<Integer> {

    DEMO("测试",1);
       /**
            * 显示值
            */
           private final String display;

           /**
            * 实际值
            */
           private final Integer value;

           DemoEnum(String display, Integer value) {
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
