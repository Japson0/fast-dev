/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.echarts;

import cn.hutool.core.util.ReflectUtil;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Echarts工具类
 *
 * @author Nick Lv
 * @created 2018 /1/2 10:31
 */
public class EChartsUtil {

    /**
     * E charts util
     */
    private EChartsUtil() {

    }


    /**
     * 不格式化
     */
    private static final int ECHARTS_FORMAT_OTHER = 4;

    /**
     * 生成echarts
     *
     * @param sourceObject the source object
     * @param valueMap     the value map
     * @return the v charts
     * @throws IllegalAccessException the illegal access exception
     * @throws InstantiationException the instantiation exception
     */
    public static VCharts generatorEChartsGraph(Class<? extends EChartsBaseEntity> sourceObject,
                                                Map<String, Object> valueMap)
            throws IllegalAccessException, InstantiationException {
        VCharts vCharts = new VCharts();
        List<Object> values = (List<Object>) valueMap.get("value");
        Object newObject = sourceObject.newInstance();
        ECharts eChartsAnno = newObject.getClass().getAnnotation(ECharts.class);
        if (eChartsAnno == null) {
            return null;
        }
        // 设置title
        VTitle vTitle = new VTitle();
        vTitle.setText(eChartsAnno.text());
        List<VTitle> titles = new ArrayList<>();
        titles.add(vTitle);
//        vTitle.setSubext(eChartsAnno.subText());
        vCharts.setTitle(titles);
        // 设置y轴
        VyAxis vyAxis = new VyAxis();
        vyAxis.setFormatter(eChartsAnno.yAxis().formatter());
        vyAxis.setType(eChartsAnno.yAxis().type());
        vCharts.setyAxis(vyAxis);
        // 设置x轴
        VxAxis vxAxis = new VxAxis();
        vxAxis.setType(eChartsAnno.xAxis().type());
        Set<String> tempX = new LinkedHashSet<>();
        Set<Date> tempDateX = new LinkedHashSet<>();
        Set<Integer> tempNumberX = new LinkedHashSet<>();
        for (Object value : values) {
            Object obj = invokeGetter(value, eChartsAnno.xAxis().valueColumn());
            if (obj instanceof Date) {
                tempDateX.add((Date) obj);
            } else if (obj instanceof Integer) {
                tempNumberX.add((Integer) obj);
            } else if (obj != null) {
                tempX.add(String.valueOf(obj));
            }
        }
        List<String> xAxisData = new ArrayList<>();
        if (!tempDateX.isEmpty()) {
            List<Date> tempDataList = new ArrayList<>();
            tempDataList.addAll(tempDateX);
            Collections.sort(tempDataList, Comparator.comparingLong(Date::getTime));
            for (Date date : tempDataList) {
                tempX.add(String.valueOf(date));
            }
        } else if (!tempNumberX.isEmpty()) {
            List<Integer> tempNumberList = new ArrayList<>();
            tempNumberList.addAll(tempNumberX);
            Collections.sort(tempNumberList);
            for (Integer number : tempNumberList) {
                tempX.add(String.valueOf(number));
            }
        }
        xAxisData.addAll(tempX);
        if (eChartsAnno.xAxis().isNotSort()) {
            Collections.sort(xAxisData);
        }
        if (eChartsAnno.dataType().equals("row")) {
            vCharts = generatorGraphRowData(vCharts, eChartsAnno, values, xAxisData);
        } else if (eChartsAnno.dataType().equals("column")) {
            vCharts = generatorGraphColumnData(vCharts, eChartsAnno, values, xAxisData);
        }
        vxAxis.setData(generatorFormatData(values, xAxisData, eChartsAnno, newObject));
        vCharts.setxAxis(vxAxis);
        if (eChartsAnno.isNeedData()) {
            vCharts.setData(values);
        }
        return vCharts;
    }

    /**
     * 格式化X轴的值
     *
     * @param values
     * @param xAxisData
     * @param eChartsAnno
     * @param newObject
     * @return
     */
    private static List<String> generatorFormatData(List<Object> values,
                                                    List<String> xAxisData,
                                                    ECharts eChartsAnno,
                                                    Object newObject) {
        int xDataType = 0;
        for (Object value : values) {
            if (invokeGetter(value, eChartsAnno.xAxis().valueColumn()) != null) {
                if (invokeGetter(value, eChartsAnno.xAxis().valueColumn()) instanceof Date) {
                    xDataType = 1;
                } else if (invokeGetter(value, eChartsAnno.xAxis().valueColumn()) instanceof Integer) {
                    xDataType = 2;
                } else {
                    xDataType = 3;
                }
            }
        }
        List<String> formatData = new ArrayList<>();
        if (xDataType == 1) {
            // 说明数据类型是日期，则调用dateFormat
            for (Object value : values) {
                List<Object> params = new ArrayList<>();
                if (invokeGetter(value, eChartsAnno.xAxis().valueColumn()) != null) {
                    params.add(String.valueOf(invokeGetter(value, eChartsAnno.xAxis().valueColumn())));
                    if (eChartsAnno.xAxis().resultType() != ECHARTS_FORMAT_OTHER) {
                        params.add(String.valueOf(eChartsAnno.xAxis().resultType()));
                    } else {
                        params.add(eChartsAnno.xAxis().dateFormat());
                    }
                    String temp = String.valueOf(invokeMethodByName(value, eChartsAnno.xAxis().dateFormatMethod(), params.toArray()));
                    formatData.add(temp);
                }

            }
        } else if (xDataType == 2) {
            // 说明数据类型是数字，则调用numberFormat
            for (String aData : xAxisData) {
                List<Object> params = new ArrayList<>();
                params.add(Integer.valueOf(aData));
                if (eChartsAnno.xAxis().resultType() != ECHARTS_FORMAT_OTHER) {
                    params.add(eChartsAnno.xAxis().resultType());
                } else {
                    params.add(eChartsAnno.xAxis().dateFormat());
                }
                String temp = String.valueOf(invokeMethodByName(newObject, eChartsAnno.xAxis().numberFormatMethod(), params.toArray()));
                formatData.add(temp);
            }
        } else {
            formatData.addAll(xAxisData);
        }
        return formatData;
    }

    /**
     * 根据列式数据生成图
     *
     * @param vCharts
     * @param eChartsAnno
     * @param values
     * @param xAxisData
     * @return
     */
    private static VCharts generatorGraphColumnData(VCharts vCharts,
                                                    ECharts eChartsAnno,
                                                    List<Object> values,
                                                    List<String> xAxisData) {
        ColumnSeries[] columnSeries = eChartsAnno.columnSeries();
        List<String> legendData = new ArrayList<>();
        List<VSeries> seriesData = new ArrayList<>();
        for (ColumnSeries series : Arrays.asList(columnSeries)) {
            legendData.add(series.cnName());
            VSeries vSeries = new VSeries();
            if (eChartsAnno.isStack()) {
                vSeries.setStack(series.stackGroup());
            }
            vSeries.setName(series.cnName());
            vSeries.setType(series.type());
            List<Object> valueList = new ArrayList<>();
            for (String xAxis : xAxisData) {
                for (Object value : values) {
                    if (xAxis.equals(String.valueOf(invokeGetter(value, series.groupByColumn())))) {
                        valueList.add(String.valueOf(invokeGetter(value, series.valueColumn()) == null ? 0 : invokeGetter(value, series.valueColumn())));

                    }
                }
            }
            vSeries.setData(valueList);
            seriesData.add(vSeries);
        }
        vCharts.setLegend(legendData);
        vCharts.setSeries(seriesData);
        return vCharts;
    }

    /**
     * 根据行式数据生成图
     *
     * @param vCharts
     * @param eChartsAnno
     * @param values
     * @param xAxisData
     * @return
     */
    private static VCharts generatorGraphRowData(VCharts vCharts,
                                                 ECharts eChartsAnno,
                                                 List<Object> values,
                                                 List<String> xAxisData) {
        // 生成图例
        Set<String> tempLegend = new HashSet<>();
        for (Object value : values) {
            Object obj = invokeGetter(value, eChartsAnno.legendColumn());
            if (obj != null) {
                tempLegend.add(String.valueOf(obj));
            }
        }
        List<String> legendData = new ArrayList<>();
        legendData.addAll(tempLegend);
        Collections.sort(legendData);
        vCharts.setLegend(legendData);
        // 生成数据
        List<VSeries> seriesList = new ArrayList<>();
        String[] valueColumns = eChartsAnno.valueColumn().split(",");
        for (int i = 0; i < valueColumns.length; i++) {
            for (String legend : legendData) {
                VSeries vSeries = new VSeries();
                List<Object> valueList = new ArrayList<>();
                for (String xAxis : xAxisData) {
                    int count = 0;
                    for (Object value : values) {
                        if (String.valueOf(invokeGetter(value, eChartsAnno.legendColumn())).equals(legend)
                                && String.valueOf(invokeGetter(value, eChartsAnno.xAxis().valueColumn())).equals(xAxis)) {
                            if (eChartsAnno.isStack()) {
                                vSeries.setStack(eChartsAnno.stackName());
                            }
                            vSeries.setType(eChartsAnno.graphType());
                            vSeries.setName(String.valueOf(invokeGetter(value, eChartsAnno.legendColumn())));
                            if (invokeGetter(value, valueColumns[i]) != null) {
                                if (String.valueOf(invokeGetter(value, valueColumns[i])).contains(".")) {
                                    valueList.add(Double.valueOf(String.valueOf(invokeGetter(value, valueColumns[i]))));
                                    count++;
                                } else {
                                    valueList.add(Long.valueOf(String.valueOf(invokeGetter(value, valueColumns[i]))));
                                    count++;

                                }
                            }
                        }
                    }
                    if (count == 0
                            && !eChartsAnno.graphType().equals("pie")
                            && !eChartsAnno.graphType().equals("ring_pie")) {
                        valueList.add(0);
                    }
                }
                vSeries.setData(valueList);
                seriesList.add(vSeries);
            }
        }
        vCharts.setSeries(seriesList);
        return vCharts;
    }


    private static Object invokeGetter(Object obj, String column) {
        try {
            return new PropertyDescriptor(column, obj.getClass())
                    .getReadMethod().invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object invokeMethodByName(Object obj, String methodName, Object... args) {
        return ReflectUtil.invoke(obj, methodName, args);
    }
}
