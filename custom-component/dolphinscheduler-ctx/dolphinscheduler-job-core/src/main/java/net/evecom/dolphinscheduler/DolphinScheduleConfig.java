package net.evecom.dolphinscheduler;

import net.evecom.dolphinscheduler.cache.SpringCache;
import net.evecom.dolphinscheduler.cache.common.JobInfo;
import net.evecom.dolphinscheduler.cache.common.ParamsCache;
import net.evecom.dolphinscheduler.core.ExecuteJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

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
    public ExecuteJob executeJob(ParamsCache paramsHandle, JobInfo jobInfo) {

        return new ExecuteJob(paramsHandle, jobInfo);
    }

    @Bean
    public ParamsCache paramsHandle(RedisTemplate redisTemplate) {
        return new SpringCache(redisTemplate);
    }

}
