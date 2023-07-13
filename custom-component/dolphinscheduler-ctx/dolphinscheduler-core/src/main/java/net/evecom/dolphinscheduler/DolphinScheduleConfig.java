package net.evecom.dolphinscheduler;

import net.evecom.dolphinscheduler.cache.CacheParamsHandle;
import net.evecom.dolphinscheduler.cache.ParamsHandle;
import net.evecom.dolphinscheduler.core.ExecuteJob;
import net.evecom.dolphinscheduler.core.JobInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P><B>DolphinScheduleConfig初始化:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月13日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Configuration
public class DolphinScheduleConfig {

    @Bean
    public ExecuteJob executeJob(ParamsHandle paramsHandle, JobInfo jobInfo) {

        return new ExecuteJob(paramsHandle, jobInfo);
    }

    @Bean
    public ParamsHandle paramsHandle() {

        return new CacheParamsHandle();
    }

}
