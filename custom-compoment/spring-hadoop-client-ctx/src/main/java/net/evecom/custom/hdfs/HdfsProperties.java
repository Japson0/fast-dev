package net.evecom.custom.hdfs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@ConfigurationProperties(prefix = "evecom.hadoop.hdfs")
public class HdfsProperties {

    /**
     * 单机模式下的namenode
     */
    private String nameNode;

    /**
     * 文件根路径
     */
    private String rootPath;

    /**
     * 单机还是集群
     */
    private HdfsMode mode;

    /**
     * 认证模式
     */
    private AuthMode authMode = AuthMode.None;

    /**
     * 登录的账号，如果是单机模式可填，否则默认是当前系统的账号
     */
    private String userName;

    /**
     * 集群信息
     */
    private Cluster cluster;

    /**
     * kerberos认证
     */
    private KerberosProperties kerberos;

    /**
     * 集群客户端
     * RevisionTrail:(Date/Author/Description)
     * 2022年10月12日
     *
     * @author Japson Huang
     */
    public class Cluster {

        /**
         * namenNode节点列表
         */
        private List<String> nameNode;

        public List<String> getNameNode() {
            return nameNode;
        }

        public void setNameNode(List<String> nameNode) {
            this.nameNode = nameNode;
        }
    }

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
    }

    public String getNameNode() {
        return nameNode;
    }

    public void setNameNode(String nameNode) {
        this.nameNode = nameNode;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public HdfsMode getMode() {
        return mode;
    }

    public void setMode(HdfsMode mode) {
        this.mode = mode;
    }

    public AuthMode getAuthMode() {
        return authMode;
    }

    public void setAuthMode(AuthMode authMode) {
        this.authMode = authMode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public KerberosProperties getKerberos() {
        return kerberos;
    }

    public void setKerberos(KerberosProperties kerberos) {
        this.kerberos = kerberos;
    }
}
