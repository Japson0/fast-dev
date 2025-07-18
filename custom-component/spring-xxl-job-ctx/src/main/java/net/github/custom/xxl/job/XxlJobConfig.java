package net.github.custom.xxl.job;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author llj
 * @since 2025/5/7 14:28
 */
@Configuration
@EnableConfigurationProperties({XXLJobProperty.class})
public class XxlJobConfig {

    @Autowired
    private XXLJobProperty xxlJobProperty;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperty.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(xxlJobProperty.getAppname());
        xxlJobSpringExecutor.setAddress(xxlJobProperty.getAddress());
        xxlJobSpringExecutor.setIp(xxlJobProperty.getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperty.getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperty.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobProperty.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperty.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}