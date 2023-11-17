package net.evecom.dolphinscheduler.callback;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年08月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface WorkStreamListener {

    void apply(WorkerStreamInfo taskParams);

    Integer getProjectCode();

    boolean match(String workStreamName);
}
