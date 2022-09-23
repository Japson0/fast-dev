/*
 * Copyright (c) 2005, 2022, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.fastdev.ddp.charts.table;

import net.evecom.fastdev.ddp.annotation.GridTable;
import net.evecom.fastdev.ddp.annotation.GroupTableInfo;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建列表工具类
 *
 * @author Nick Lv
 * @version --添加版本信息
 * @created 2017 /05/26 10:09:22
 * @see --添加类中引用的相关类和接口
 * @since Version 1.0
 */
public class GridTableUtil {

    /**
     * 缓存类
     */
    private static final Map<Class<?>, GroupClass[]> CACHE_TABLE = new ConcurrentHashMap<>();

    /**
     * Grid table util
     */
    private GridTableUtil() {
    }

    public static Collection<GridTableInfo> createGridTable(Class<?> sourceObject) {
        return createGridTable(sourceObject, null);
    }

    /**
     * 创建列表
     *
     * @param sourceObject the source object
     * @return the list
     * @author Nick Lv
     * @created 2017 /05/26 10:09:22 Create grid table list.
     */
    public static Collection<GridTableInfo> createGridTable(Class<?> sourceObject, Class<?> group) {

        GroupClass[] groupClass = CACHE_TABLE.computeIfAbsent(sourceObject, c -> {
            try {
                Field[] fields = sourceObject.getDeclaredFields();
                List<GroupClass> groupClasses = new ArrayList<>(fields.length);
                for (Field field : fields) {
                    GridTable gridTable = field.getAnnotation(GridTable.class);
                    if (gridTable != null) {
                        String fieldName = gridTable.enName();
                        if (StringUtils.isEmpty(fieldName)) {
                            fieldName = field.getName();
                        }
                        groupClasses.add(new GroupClass(gridTable, fieldName));
                    }
                }
                if (groupClasses.size() == 0) {
                    throw new UnsupportedOperationException("类:【" + sourceObject.getName() + " 】不存在表格注解，请仔细审查");
                }
                return groupClasses.toArray(new GroupClass[0]);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });
        return createTableInfo(groupClass, group);
    }

    private static Collection<GridTableInfo> createTableInfo(GroupClass[] groupClass, Class<?> group) {
        Collection<GridTableInfo> gridTableInfos = new TreeSet<>((a1, a2) -> {
            if (a1.getViewOrder().equals(a2.getViewOrder())) {
                return 1;
            } else return a1.getViewOrder().compareTo(a2.getViewOrder());
        });
        if (group == null) {
            for (GroupClass gc : groupClass) {
                GridTableInfo gridTableInfo = new GridTableInfo();
                GridTable gridTable = gc.gridTables;
                gridTableInfo.setCnName(gridTable.cnName());
                gridTableInfo.setEnName(gc.enName);
                render(gridTableInfo, gridTable);
                gridTableInfos.add(gridTableInfo);
            }
        } else {
            for (GroupClass gc : groupClass) {
                GroupTableInfo[] groups = gc.gridTables.group();
                if (groups.length > 0) {
                    for (GroupTableInfo groupTableInfo : groups) {
                        if (groupTableInfo.group() == group) {
                            GridTableInfo gridTableInfo = new GridTableInfo();
                            gridTableInfo.setCnName(gridTableInfo.getCnName());
                            gridTableInfo.setEnName(gc.enName);
                            if (groupTableInfo.extend()) {
                                render(gridTableInfo, gc.gridTables);
                            } else {
                                gridTableInfo.setStyle(groupTableInfo.style());
                                gridTableInfo.setStyleClass(groupTableInfo.styleClass());
                                gridTableInfo.setNotDate(groupTableInfo.isNotDate());
                                gridTableInfo.setViewOrder(groupTableInfo.viewOrder());
                                gridTableInfo.setCanClick(groupTableInfo.canClick());
                                gridTableInfo.setDateFormat(groupTableInfo.dateFormat());
                                gridTableInfo.setWidth(groupTableInfo.width());
                                gridTableInfo.setDictionary(gridTableInfo.isDictionary());
                            }
                            gridTableInfos.add(gridTableInfo);
                            break;
                        }
                    }
                }
            }
        }
        return gridTableInfos;
    }

    private static void render(GridTableInfo gridTableInfo, GridTable gridTable) {
        gridTableInfo.setStyle(gridTable.style());
        gridTableInfo.setStyleClass(gridTable.styleClass());
        gridTableInfo.setNotDate(gridTable.isNotDate());
        gridTableInfo.setViewOrder(gridTable.viewOrder());
        gridTableInfo.setCanClick(gridTable.canClick());
        gridTableInfo.setDateFormat(gridTable.dateFormat());
        gridTableInfo.setWidth(gridTable.width());
        gridTableInfo.setDictionary(gridTable.isDictionary());
    }


    static class GroupClass {

        /**
         * 组别
         */
        private GridTable gridTables;

        /**
         * 字段名称
         */
        private String enName;

        public GroupClass(GridTable gridTables, String enName) {
            this.gridTables = gridTables;
            this.enName = enName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GroupClass that = (GroupClass) o;
            return Objects.equals(enName, that.enName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(enName);
        }
    }
}



