package net.evecom.dolphinscheduler.core;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import net.evecom.dolphinscheduler.cache.common.JobInfo;
import net.evecom.dolphinscheduler.cache.common.ParamsCache;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <P><B>执行器任务:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Component
public class ExecuteJob implements ApplicationRunner {

    /**
     * 缓存处理器
     */
    private final ParamsCache paramsHandle;

    /**
     * 任务信息
     */
    private final JobInfo jobInfo;

    /**
     * job参数信息
     */
    private JobParams jobParams;

    private final String CURRENT_JOB_ID = "currentJobId";

    private final String PRE_JOB_ID = "preJobId";


    public ExecuteJob(ParamsCache paramsHandle, JobInfo jobInfo) {
        this.paramsHandle = paramsHandle;
        this.jobInfo = jobInfo;
    }

    public void execute() throws Exception {

        String params = null;
        if (jobParams.getPreJobId() != null) {
            params = paramsHandle.get(jobParams.getPreJobId());
        }
        System.out.println("currentJobId" + jobParams.getJobId());
        System.out.println("preJobId" + jobParams.getPreJobId());
        String result = jobInfo.execute(jobParams.getParams(), params);
        if (StringUtils.isNotEmpty(result) && jobParams.getJobId() != null) {
            paramsHandle.put(jobParams.getJobId(), result);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] sourceArgs = args.getSourceArgs();
        Map<String, String> params = new HashMap<>(sourceArgs.length);
        for (String sourceArg : sourceArgs) {
            String[] split = sourceArg.split("=");
            params.put(split[0], split[1]);
        }
        String preJobId = System.getProperty(PRE_JOB_ID);
        String currJobId = System.getProperty(CURRENT_JOB_ID);
        this.jobParams = new JobParams(preJobId, currJobId, params);
        execute();
    }
}
