package com.nlecloud.spring.annotation.enums;

/**
 * 审计操作类型
 *
 * @author warrior
 */
public enum OperatorType {

    ADD(0),
    UPDATE(1),
    DELETE(2);


    private final int value;

    OperatorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
