package net.evecom.dolphinscheduler.core;

import java.util.Map;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月12日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class JobParams {


    /**
     * 缓存参数KEY格式
     */
    private final String JOB_ID_FORMAT = "JOB_PARAMS_CACHE_ID_%s";


    /**
     * 当前JOBID
     */
    private String jobId;

    /**
     * 前JOBID
     */
    private String preJobId;
    private Map<String, String> params;


    public JobParams(String preJobId, String currentJobId, Map<String, String> params) {
        if (preJobId != null) {
            this.preJobId = String.format(JOB_ID_FORMAT, preJobId);
        }
        if (currentJobId != null) {
            this.jobId = String.format(JOB_ID_FORMAT, currentJobId);
        }
        this.params = params;
    }


    public String getJobId() {
        return jobId;
    }

    public String getPreJobId() {
        return preJobId;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
