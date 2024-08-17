package net.evecom.fastdev.mybatis.annotation;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * <P><B>分页返回类:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2024年08月15日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class PageResponse<P,DTO> extends PageRequest<P> {

    /**
     * 记录
     */
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty("返回数据")
    protected List<DTO> records;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private long total;

    @JsonUnwrapped
    private final PageRequest pageRequest;


    public PageResponse(PageRequest pageRequest, List<DTO> records) {
        this(pageRequest,records,records==null?0:records.size());
    }

    public PageResponse(PageRequest pageRequest, List<DTO> records, long total) {
        this.pageRequest = pageRequest;
        this.total = total;
        this.records = records;
    }

    public List<DTO> getRecords() {
        return records;
    }

    public PageResponse<P,DTO> setRecords(List<DTO> records) {
        this.records = records;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PageResponse<P,DTO> setTotal(long total) {
        this.total = total;
        return this;
    }

    public PageRequest getPageConditionQuery() {
        return pageRequest;
    }
}
