package net.evecom.custom.hdfs;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <P><B>hadoop配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月13日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@ConfigurationProperties(prefix = "evecom.hadoop")
public class HadoopProperties {

    /**
     * 认证模式
     */
    private AuthMode authMode = AuthMode.None;

    /**
     * kerberos认证
     */
    private KerberosProperties kerberos;

    /**
     * kerberos
     * RevisionTrail:(Date/Author/Description)
     * 2022年10月12日
     *
     * @author Japson Huang
     */
    public static class KerberosProperties {

        /**
         * 认证信息
         */
        private String principal;

        /**
         * 客户端key配置路径
         */
        private String keyTabPath;

        /**
         * kerberos配置路径
         */
        private String krbConfPath;

        /**
         * 刷新ticket时间，单位s
         */
        private long refreshTick = 60;


        public String getKeyTabPath() {
            return keyTabPath;
        }

        public void setKeyTabPath(String keyTabPath) {
            this.keyTabPath = keyTabPath;
        }

        public String getKrbConfPath() {
            return krbConfPath;
        }

        public void setKrbConfPath(String krbConfPath) {
            this.krbConfPath = krbConfPath;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public long getRefreshTick() {
            return refreshTick;
        }

        public void setRefreshTick(long refreshTick) {
            this.refreshTick = refreshTick;
        }
    }

    public AuthMode getAuthMode() {
        return authMode;
    }

    public void setAuthMode(AuthMode authMode) {
        this.authMode = authMode;
    }

    public KerberosProperties getKerberos() {
        return kerberos;
    }

    public void setKerberos(KerberosProperties kerberos) {
        this.kerberos = kerberos;
    }


}
