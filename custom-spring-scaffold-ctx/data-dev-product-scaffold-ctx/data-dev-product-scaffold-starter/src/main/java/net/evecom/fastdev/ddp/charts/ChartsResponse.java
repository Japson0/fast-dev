package net.evecom.fastdev.ddp.charts;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.ddp.charts.table.GridData;
import net.evecom.fastdev.ddp.charts.table.GridTableInfo;
import net.evecom.fastdev.ddp.charts.table.GridTableUtil;
import net.evecom.fastdev.mybatis.annotation.PageConditionQuery;

import java.util.Collection;

/**
 * <P><B>图标返回类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年08月24日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ChartsResponse<T> extends RestResponse<T> {


    /**
     * 生成带分页表格信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @param page      实际数据
     * @param tableView 表格类
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<IPage<T>>> tableResponse(IPage<T> page, Class<?> tableView) {
        return tableResponse(page, tableView, null);
    }

    /**
     * 生成带分页表格信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @param page      实际数据
     * @param tableView 表格类
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<PageConditionQuery<T>>> tableResponse(PageConditionQuery<T> page, Class<?> tableView) {
        return tableResponse(page, tableView, null);
    }

    /**
     * 生成带分页表格信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @param page      实际数据
     * @param tableView 表格类
     * @param group     分组信息
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<PageConditionQuery<T>>> tableResponse(PageConditionQuery<T> page, Class<?> tableView, Class<?> group) {
        Collection<GridTableInfo> gridTable = GridTableUtil.createGridTable(tableView, group);
        ChartsResponse<GridData<PageConditionQuery<T>>> result = new ChartsResponse<>();
        result.setData(new GridData<>(page, gridTable));
        return result;
    }

    /**
     * 生成带分页表格信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @param page      实际数据
     * @param tableView 表格类
     * @param group     分组信息
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<IPage<T>>> tableResponse(IPage<T> page, Class<?> tableView, Class<?> group) {
        Collection<GridTableInfo> gridTable = GridTableUtil.createGridTable(tableView, group);
        ChartsResponse<GridData<IPage<T>>> result = new ChartsResponse<>();
        result.setData(new GridData<>(page, gridTable));
        return result;
    }

    /**
     * 不带分页表格的信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @param data      实际数据
     * @param tableView 表格类
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<Collection<T>>> tableResponse(Collection<T> data, Class<?> tableView) {
        return tableResponse(data, tableView, null);
    }

    /**
     * 不带分页表格的信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @param data      实际数据
     * @param tableView 表格类
     * @param group     分组信息
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<Collection<T>>> tableResponse(Collection<T> data, Class<?> tableView, Class<?> group) {
        Collection<GridTableInfo> gridTable = GridTableUtil.createGridTable(tableView, group);
        ChartsResponse<GridData<Collection<T>>> result = new ChartsResponse<>();
        result.setData(new GridData<>(data, gridTable));
        return result;
    }

}
