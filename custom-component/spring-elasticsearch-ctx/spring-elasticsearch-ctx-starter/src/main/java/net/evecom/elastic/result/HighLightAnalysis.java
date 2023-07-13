package net.evecom.elastic.result;

import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;

/**
 * <P><B>高亮内容解析器:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年06月14日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class HighLightAnalysis<R> implements ResultAnalysis<R> {

    /**
     * 高亮配置
     */
    private Highlight highlight;

    /**
     * 是否map
     */
    private boolean isMap;

    public HighLightAnalysis(Highlight highlight,ResultType resultType) {
        this.highlight = highlight;
        this.isMap=resultType==ResultType.MAP;
    }

    @Override
    public R apply(R r,Hit<R> hit) {
        Map<String, List<String>> highlightValue = hit.highlight();
        if (isMap) {
            Map<String, Object> temp = (Map<String, Object>) r;
            for (Map.Entry<String, List<String>> entry : highlightValue.entrySet()) {
                temp.put(entry.getKey(), entry.getValue().get(0));
            }
        } else {
            BeanMap beanMap = BeanMap.create(r);
            for (Map.Entry<String, List<String>> entry : highlightValue.entrySet()) {
                beanMap.put(entry.getKey(), entry.getValue().get(0));
            }
            r = (R) beanMap.getBean();
        }
        return r;
    }
}
