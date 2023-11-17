package net.evecom.custom.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

import java.util.List;

import static org.apache.hadoop.fs.CommonConfigurationKeysPublic.FS_DEFAULT_NAME_KEY;

/**
 * <P><B>hdfs客户端工厂:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
public class HdfsClientFactory implements FactoryBean<HdfsClient> {

    /**
     * hdfs配置
     */
    private final HdfsProperties hdfsProperties;

    /**
     * haddoop配置
     */

    public HdfsClientFactory(HdfsProperties hdfsProperties) {
        this.hdfsProperties = hdfsProperties;
    }

    @Override
    public HdfsClient getObject() throws Exception {
        HdfsMode mode = hdfsProperties.getMode();
        Configuration configuration = new Configuration();
        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        if (mode == HdfsMode.SINGLE) {
            configuration.set(FS_DEFAULT_NAME_KEY, hdfsProperties.getNameNode());
            System.setProperty("HADOOP_USER_NAME", hdfsProperties.getUserName());
        }
        if (mode == HdfsMode.CLUSTER) {
            configuration.set(FS_DEFAULT_NAME_KEY, "hdfs://" + hdfsProperties.getCluster().getNameService());
            configuration.set("dfs.nameservices", hdfsProperties.getCluster().getNameService());
            HdfsProperties.Cluster cluster = hdfsProperties.getCluster();
            List<String> nameNode = cluster.getNameNode();
            Assert.notEmpty(nameNode, "hdfs nameNode must not be null!");
            String nameNameFor = "namenode%s";
            String nodeNameForPrefix = "dfs.namenode.rpc-address.nameservice.";
            StringBuilder nameNodeResult = new StringBuilder();
            String tempName;
            for (int i = 0; i < nameNode.size(); i++) {
                tempName = String.format(nameNameFor, i);
                configuration.set(nodeNameForPrefix + tempName, nameNode.get(i));
                nameNodeResult.append(tempName);
                if (i != nameNode.size() - 1) {
                    nameNodeResult.append(",");
                }
            }
            configuration.set("dfs.ha.namenodes.nameservice", nameNodeResult.toString());
            configuration.set("dfs.client.failover.proxy.provider.nameservice",
                    "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        }
        return new HdfsClient(hdfsProperties.getRootPath(), configuration);

    }


    @Override
    public Class<?> getObjectType() {
        return HdfsClient.class;
    }


    public static void main(String[] args) throws Exception {
        HadoopProperties hadoopProperties = new HadoopProperties();
        HadoopProperties.KerberosProperties kerberosProperties = new HadoopProperties.KerberosProperties();
        kerberosProperties.setPrincipal("hekai/client-179.edp.com@EDP.COM");
        kerberosProperties.setKeyTabPath("C:/Users/HP/Desktop/fsdownload/hekai");
        kerberosProperties.setKrbConfPath("C:\\ProgramData\\MIT\\Kerberos5\\krb5.ini");
        hadoopProperties.setAuthMode(AuthMode.Kerberos);
        hadoopProperties.setKerberos(kerberosProperties);
        HadoopConfig hadoopConfig = new HadoopConfig(hadoopProperties);

        HdfsProperties hdfsProperties1 = new HdfsProperties();
        hdfsProperties1.setUserName("hekai");
        hdfsProperties1.setMode(HdfsMode.SINGLE);
        hdfsProperties1.setNameNode("hdfs://cn-176.edp.com:8020");
        HdfsClientFactory hdfsClientFactory = new HdfsClientFactory(hdfsProperties1);
        HdfsClient object = hdfsClientFactory.getObject();
        List<FileStatus> list = object.list("/");
        System.out.println(list.size());
    }
}
