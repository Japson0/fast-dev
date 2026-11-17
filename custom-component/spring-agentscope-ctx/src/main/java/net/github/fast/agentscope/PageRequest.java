package net.github.fast.agentscope;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/** Self-contained pagination request and response structure. */
public class PageRequest<P> implements Serializable {
    private static final long serialVersionUID = 5257379308853253918L;

    private long page;

    private long size = 20;

    @JsonProperty(value = "queryParams", access = JsonProperty.Access.WRITE_ONLY)
    private P condition;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long total;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<P> records = List.of();

    public PageRequest() { }

    public static <P> PageRequest<P> result(long page, long size, long total, List<P> records) {
        var result = new PageRequest<P>();
        result.setPage(page);
        result.setSize(size);
        result.setTotal(total);
        result.setRecords(records);
        return result;
    }

    public long getPage() { return page; }
    public void setPage(long page) { this.page = page; }
    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }
    public P getCondition() { return condition; }
    public void setCondition(P condition) { this.condition = condition; }
    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }
    public List<P> getRecords() { return records; }
    public void setRecords(List<P> records) { this.records = records; }
}
