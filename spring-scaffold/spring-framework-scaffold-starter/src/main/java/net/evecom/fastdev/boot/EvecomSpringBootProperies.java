package net.evecom.fastdev.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>SpringBoot配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2020年10月16日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@ConfigurationProperties(prefix = "evecom")
public class EvecomSpringBootProperies {

    /**
     * 传输过滤器信息
     */
    private WebTransSecurityFilter webTransSecurityFilter = new WebTransSecurityFilter();

    /**
     * 枚举配置
     */
    private EnumsProperties enums;

    public static class WebTransSecurityFilter {
        private boolean enable = true;

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

    public EnumsProperties getEnums() {
        return enums;
    }

    public void setEnums(EnumsProperties enums) {
        this.enums = enums;
    }

    /**
     * 枚举配置
     * RevisionTrail:(Date/Author/Description)
     * 2022年09月14日
     *
     * @author Japson Huang
     */
    public static class EnumsProperties {
        /**
         * 前缀包名
         */
        private String prefixPackage;

        public String getPrefixPackage() {
            return prefixPackage;
        }

        public void setPrefixPackage(String prefixPackage) {
            this.prefixPackage = prefixPackage;
        }
    }
}
