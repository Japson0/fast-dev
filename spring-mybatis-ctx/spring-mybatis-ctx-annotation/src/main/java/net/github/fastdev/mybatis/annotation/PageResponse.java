package net.github.fastdev.mybatis.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * <P><B>分页返回类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年08月15日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Schema(name = "分页返回类")
public class PageResponse<DTO> extends PageRequest {

    /**
     * 记录
     */
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(name = "返回数据")
    @JsonProperty("data")
    protected List<DTO> records;

    /**
     * 总数
     */
    @Schema(name = "总数")
    @JsonSerialize(using = LongSerializer.class)

    private long total;



    public PageResponse(PageRequest pageRequest, List<DTO> records) {
        this(pageRequest,records,records==null?0:records.size());
    }

    public PageResponse(PageRequest pageRequest, List<DTO> records, long total) {
        this.total = total;
        this.records = records;
        this.setSize(pageRequest.getSize());
        this.setPage(pageRequest.getPage());
    }

    public List<DTO> getRecords() {
        return records;
    }

    public PageResponse<DTO> setRecords(List<DTO> records) {
        this.records = records;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PageResponse<DTO> setTotal(long total) {
        this.total = total;
        return this;
    }
}
