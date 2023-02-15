/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * eCharts格式化方法
 *
 * @author Nick Lv
 * @created 2018 /1/4 10:01
 */
public class EChartsBaseEntity implements Serializable {

    /**
     * 格式化成星期几
     */
    private static final int ECHARTS_FORMAT_WEEK = 1;
    /**
     * 格式化成月份
     */
    private static final int ECHARTS_FORMAT_MONTH = 2;
    /**
     * 格式化成日期，此方法为默认格式,例如2017/11/25
     */
    private static final int ECHARTS_FORMAT_DATE = 3;
    /**
     * 不格式化
     */
    private static final int ECHARTS_FORMAT_OTHER = 4;
    /**
     * 星期几
     */
    private static final String[] WEEKS = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    /**
     * 月份
     */
    private static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月"
            , "七月", "八月", "九月", "十月", "十一月", "十二月"};

    /**
     * 将数字formart成星期或者月份
     *
     * @param number       the number
     * @param wantToFormat the want to format
     * @return the string
     */
    public String numberFormat(Integer number, Integer wantToFormat) {
        String result = "";
        int needFormatNum = number - 1;
        if (needFormatNum < 0 || needFormatNum > 12) {
            return result;
        }
        if (wantToFormat == ECHARTS_FORMAT_WEEK) {
            result = WEEKS[needFormatNum];
        } else if (wantToFormat == ECHARTS_FORMAT_MONTH) {
            result = MONTHS[needFormatNum];
        } else if (wantToFormat == ECHARTS_FORMAT_OTHER) {
            result = String.valueOf(number);
        }
        return result;
    }

    /**
     * 默认的格式化日期方法
     *
     * @param dateString   the date string
     * @param formatString the format string
     * @return the string
     */
    public String dateFormat(String dateString, String formatString) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date needFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        c.setTime(needFormat);
        String result = "";
        if (formatString.length() > 1) {
            result = new SimpleDateFormat(formatString).format(needFormat);
        } else {
            int wantToFormat = Integer.parseInt(formatString);
            if (wantToFormat == ECHARTS_FORMAT_WEEK) {
                result = new SimpleDateFormat("EEEE").format(c.getTime());
            } else if (wantToFormat == ECHARTS_FORMAT_MONTH) {
                result = new SimpleDateFormat("MMMM").format(c.getTime());
            } else if (wantToFormat == ECHARTS_FORMAT_DATE) {
                result = new SimpleDateFormat("yyyy/MM/dd").format(c.getTime());
            } else if (wantToFormat == ECHARTS_FORMAT_OTHER) {
                result = "";
            }
        }

        return result;
    }
}
