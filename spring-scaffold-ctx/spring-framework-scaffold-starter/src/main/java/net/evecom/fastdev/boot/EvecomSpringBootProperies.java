package net.evecom.fastdev.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>SpringBoot配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月16日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@ConfigurationProperties(prefix = "evecom")
public class EvecomSpringBootProperies {

    /**
     * 传输过滤器信息
     */
    private WebTransSecurityFilter webTransSecurityFilter = new WebTransSecurityFilter();

    public static class WebTransSecurityFilter {
        boolean enable = true;

        private String path = "/**";

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    public WebTransSecurityFilter getWebTransSecurityFilter() {
        return webTransSecurityFilter;
    }

    public void setWebTransSecurityFilter(WebTransSecurityFilter webTransSecurityFilter) {
        this.webTransSecurityFilter = webTransSecurityFilter;
    }
}
