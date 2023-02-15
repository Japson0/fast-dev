/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.io.Serializable;
import java.util.List;

/**
 * 数据
 *
 * @author Nick Lv
 * @created 2017 /12/21 20:16
 */
public class VSeries implements Serializable {
    /**
     * 名字
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 堆叠
     */
    private String stack;
    /**
     * 数据
     */
    private List<Object> data;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<Object> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(List<Object> data) {
        this.data = data;
    }

    /**
     * Gets stack.
     *
     * @return the stack
     */
    public String getStack() {
        return stack;
    }

    /**
     * Sets stack.
     *
     * @param stack the stack
     */
    public void setStack(String stack) {
        this.stack = stack;
    }

    /**
     * Builder builder
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder
     *
     * @author Nick Lv
     */
    public static final class Builder {
        /**
         * Name
         */
        private String name;
        /**
         * Type
         */
        private String type;
        /**
         * Stack
         */
        private String stack;
        /**
         * Data
         */
        private List<Object> data;

        /**
         * Builder
         */
        private Builder() {
        }


        /**
         * Name builder
         *
         * @param name name
         * @return the builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Type builder
         *
         * @param type type
         * @return the builder
         */
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * Stack builder
         *
         * @param stack stack
         * @return the builder
         */
        public Builder stack(String stack) {
            this.stack = stack;
            return this;
        }

        /**
         * Data builder
         *
         * @param data data
         * @return the builder
         */
        public Builder data(List<Object> data) {
            this.data = data;
            return this;
        }

        /**
         * Build v series
         *
         * @return the v series
         */
        public VSeries build() {
            VSeries vSeries = new VSeries();
            vSeries.setName(name);
            vSeries.setType(type);
            vSeries.setStack(stack);
            vSeries.setData(data);
            return vSeries;
        }
    }
}
