package net.evecom.custom.hdfs;

import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static org.apache.hadoop.fs.CommonConfigurationKeysPublic.*;

/**
 * <P><B>hadoop配置:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年10月12日 CREATE
 *
 * @author Japson Huang
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties({HadoopProperties.class, HdfsProperties.class})
public class HadoopConfig {


//    private final org.apache.hadoop.conf.Configuration configuration;

    public HadoopConfig(HadoopProperties hadoopProperties) throws IOException {
        AuthMode authMode = hadoopProperties.getAuthMode();
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        if (authMode == AuthMode.Kerberos) {
            configuration.set(HADOOP_SECURITY_AUTHENTICATION, hadoopProperties.getAuthMode().getName());
            HadoopProperties.KerberosProperties kerberos = hadoopProperties.getKerberos();
            System.setProperty("java.security.krb5.conf", kerberos.getKrbConfPath());
            configuration.set(HADOOP_KERBEROS_KEYTAB_LOGIN_AUTORENEWAL_ENABLED, "true");
            configuration.set(HADOOP_KERBEROS_MIN_SECONDS_BEFORE_RELOGIN, String.valueOf(kerberos.getRefreshTick()));
            UserGroupInformation.setConfiguration(configuration);
            UserGroupInformation.loginUserFromKeytab(kerberos.getPrincipal(), kerberos.getKeyTabPath());
        }
    }


    /**
     * hdfs客户端
     * RevisionTrail:(Date/Author/Description)
     * 2022年10月12日
     *
     * @author Japson Huang
     */
    @Bean
    @ConditionalOnExpression("#{environment['evecom.hadoop.hdfs.mode']!=null}")
    public HdfsClientFactory hdfsClientFactory(HdfsProperties hdfsProperties) {
        return new HdfsClientFactory(hdfsProperties);
    }
}
