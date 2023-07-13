package net.evecom.dolphinscheduler.core;

import java.util.Map;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年07月11日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface JobInfo {

    String execute(Map<String, String> currentParams, String preParams) throws Exception;

}
