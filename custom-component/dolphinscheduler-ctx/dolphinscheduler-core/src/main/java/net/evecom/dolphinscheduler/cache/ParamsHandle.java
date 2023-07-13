package net.evecom.dolphinscheduler.cache;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface ParamsHandle {

    String getParams(String jobId);

    String putParams(String jobId, String params);
}
