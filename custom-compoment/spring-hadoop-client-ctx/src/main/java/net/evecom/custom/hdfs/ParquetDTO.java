package net.evecom.custom.hdfs;

import java.util.List;
import java.util.Map;

/**
 * <P><B>parques格式:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年07月25日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ParquetDTO {

    /**
     * 数据信息
     */
    private List<Map<String, Object>> datas;

    public ParquetDTO() {
    }

    public ParquetDTO(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }


    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }
}
