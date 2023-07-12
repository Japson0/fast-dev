package net.evecom.custom.hdfs;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
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
     * 登录的账号，如果是单机模式可填，否则默认是当前系统的账号
     */
    private String userName;

    /**
     * 集群信息
     */
    private Cluster cluster;

    /**
     * 集群客户端
     * RevisionTrail:(Date/Author/Description)
     * 2022年10月12日
     *
     * @author Japson Huang
     */
    public static class Cluster {

        /**
         * 群节点key
         */
        private String nameService = "nameservice";
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

        public String getNameService() {
            return nameService;
        }

        public void setNameService(String nameService) {
            this.nameService = nameService;
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

}
