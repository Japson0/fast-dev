/*
 * Copyright (c) 2005, 2021, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package net.evecom.fastdev.boot.utils;

import java.util.Collection;
import java.util.function.Function;

/**
 * <P><B>数组工具类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2021年11月25日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class ArraysUtils {

    /**
     * 拼接字符串
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月25日
     *
     * @author Japson Huang
     */
    public static String join(Collection<String> array, String delimiter) {

        return join(array, String::toString, delimiter);
    }

    /**
     * 拼接字符串
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月25日
     *
     * @author Japson Huang
     */
    public static String join(String[] array, String delimiter) {

        return join(array, String::toString, delimiter);
    }

    /**
     * 拼接对象数组
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月25日
     *
     * @author Japson Huang
     */
    public static <T> String join(T[] array, Function<T, String> consumer, String delimiter) {
        StringBuilder str = new StringBuilder();
        int size = array.length;
        for (int i = 0; i < size; i++) {
            str.append(consumer.apply(array[i]));
            if (i != size - 1) {
                str.append(delimiter);
            }
        }
        return str.toString();
    }

    /**
     * 拼接对象数组
     * RevisionTrail:(Date/Author/Description)
     * 2021年11月25日
     *
     * @author Japson Huang
     */
    public static <T> String join(Collection<T> array, Function<T, String> consumer, String delimiter) {
        StringBuilder str = new StringBuilder();
        int size = array.size();
        int i = 0;
        for (T t : array) {
            str.append(consumer.apply(t));
            if (i++ != size - 1) {
                str.append(delimiter);
            }
        }
        return str.toString();
    }

}
