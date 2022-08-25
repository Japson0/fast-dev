package net.evecom.fastdev.ddp.charts;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.evecom.fastdev.common.model.RestResponse;
import net.evecom.fastdev.ddp.charts.table.GridData;
import net.evecom.fastdev.ddp.charts.table.GridTableInfo;
import net.evecom.fastdev.ddp.charts.table.GridTableUtil;

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
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<IPage<T>>> tableResponse(IPage<T> page, Class<?> tableView) {
        Collection<GridTableInfo> gridTable = GridTableUtil.createGridTable(tableView);
        ChartsResponse<GridData<IPage<T>>> result = new ChartsResponse<>();
        result.setData(new GridData<>(page, gridTable));
        return result;
    }

    /**
     * 不带分页表格的信息
     * RevisionTrail:(Date/Author/Description)
     * 2022年08月25日
     *
     * @author Japson Huang
     */
    public static <T> ChartsResponse<GridData<Collection<T>>> tableResponse(Collection<T> data, Class<?> tableView) {
        Collection<GridTableInfo> gridTable = GridTableUtil.createGridTable(tableView);
        ChartsResponse<GridData<Collection<T>>> result = new ChartsResponse<>();
        result.setData(new GridData<>(data, gridTable));
        return result;
    }


}
