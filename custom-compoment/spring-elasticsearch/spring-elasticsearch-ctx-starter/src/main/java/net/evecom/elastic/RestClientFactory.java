package net.evecom.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2022年11月08日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class RestClientFactory implements FactoryBean<ElasticsearchClient>, InitializingBean, DisposableBean {

    /**
     * rest客户端
     */
    private RestClient restClient;

    /**
     * ES配置
     */
    private final ElasticSearchProperties elasticSearchProperties;

    public RestClientFactory(ElasticSearchProperties elasticSearchProperties) {
        this.elasticSearchProperties = elasticSearchProperties;
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public ElasticsearchClient getObject() throws Exception {
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

    @Override
    public Class<?> getObjectType() {
        return ElasticsearchClient.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(elasticSearchProperties.getUsername(), elasticSearchProperties.getPassword()));


        RestClientBuilder builder = RestClient.builder(buildHttpHost())
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
//                        .setSSLContext(finalSslContext)
//                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                        .setDefaultCredentialsProvider(credentialsProvider));
        this.restClient = builder.build();
    }

    private SSLContext buildSSL(String path) throws Exception {

        Path caCertificatePath = Paths.get(path);
        SSLContext sslContext = null;
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            Certificate trustedCa;
            try (InputStream is = Files.newInputStream(caCertificatePath)) {
                trustedCa = factory.generateCertificate(is);
            }
            KeyStore trustStore = KeyStore.getInstance("pkcs12");
            trustStore.load(null, null);
            trustStore.setCertificateEntry("ca", trustedCa);
            SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                    .loadTrustMaterial(trustStore, null);
        } catch (Exception e) {
            throw new IllegalArgumentException("ES 连接认证失败");
        }
        return sslContext;
    }

    private HttpHost[] buildHttpHost() {
        String[] hosts = elasticSearchProperties.getClusterNodes().split(",");
        String protocol = elasticSearchProperties.getProtocol();
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        int p = 0;
        for (String node : hosts) {
            HttpHost httpHost;
            //分割host和端口
            String[] hostAndPort = node.split(":");

            Assert.isTrue(hostAndPort.length == 2,
                    String.format("在集群节点中, node:[%s]存在错误 ! 格式必须是host:port!", node));

            String host = hostAndPort[0].trim();
            String port = hostAndPort[1].trim();

            Assert.hasText(host, String.format("在node:[%s]没找到host!", node));
            Assert.hasText(port, String.format("在node:[%s]没找到port!", node));

            if (protocol != null) {
                httpHost = new HttpHost(host, Integer.parseInt(port), protocol);
            } else {
                httpHost = new HttpHost(host, Integer.parseInt(port));
            }
            httpHosts[p] = httpHost;
        }
        return httpHosts;
    }
}
