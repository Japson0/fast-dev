package net.evecom.custom.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

/**
 * <P><B>hdfs客户端工厂:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class HdfsClientFactory implements FactoryBean<HdfsClient> {

    /**
     * hdfs配置
     */
    private final HdfsProperties hdfsProperties;

    public HdfsClientFactory(HdfsProperties hdfsProperties) {
        this.hdfsProperties = hdfsProperties;
    }

    @Override
    public HdfsClient getObject() throws Exception {
        HdfsMode mode = hdfsProperties.getMode();
        Configuration configuration = new Configuration();
        configuration.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        if (mode == HdfsMode.SINGLE) {
            configuration.set("fs.defaultFS", hdfsProperties.getNameNode());
            System.setProperty("HADOOP_USER_NAME", hdfsProperties.getUserName());
        }
        if (mode == HdfsMode.CLUSTER) {
            configuration.set("fs.defaultFS", "hdfs://nameservice");
            configuration.set("dfs.nameservices", "nameservice");
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
        HdfsClient hdfsClient = new HdfsClient(configuration);
        initAuth(configuration, hdfsClient);
        return hdfsClient;

    }

    private void initAuth(Configuration configuration, HdfsClient client) throws IOException {
        if (hdfsProperties.getAuthMode() == AuthMode.None) {
            return;
        }
        configuration.set("hadoop.security.authentication", hdfsProperties.getAuthMode().getName());
        if (hdfsProperties.getAuthMode() == AuthMode.Kerberos) {
            HdfsProperties.KerberosProperties kerberos = hdfsProperties.getKerberos();
            System.setProperty("java.security.krb5.conf", kerberos.getKrbConfPath());
            UserGroupInformation.setConfiguration(configuration);
            UserGroupInformation userGroupInformation = UserGroupInformation.loginUserFromKeytabAndReturnUGI("hekai/client-179.edp.com@EDP.COM", kerberos.getKeyTabPath());
            new AuthProxy(client, userGroupInformation);
        }
    }

    @Override
    public Class<?> getObjectType() {
        return HdfsClient.class;
    }


}
