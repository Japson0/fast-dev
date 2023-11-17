package net.evecom.dolphinscheduler.callback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年08月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class WorkStreamCallBack {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     */
    private final List<WorkStreamListener> workStreamListeners;

    public WorkStreamCallBack(List<WorkStreamListener> workStreamListeners) {
        this.workStreamListeners = workStreamListeners;
    }

    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public void apply(String content) throws JsonProcessingException, ParseException {
        if (workStreamListeners.size() != 0) {
            JsonNode result = objectMapper.readTree(content);
            String processName = result.get("processName").asText();
            Integer projectCode = result.get("projectCode").asInt();
            String workName = getWorkName(processName);
            WorkStreamListener workStreamListener = workStreamListeners.stream()
                    .filter(f -> projectCode.equals(f.getProjectCode()) && f.match(workName))
                    .findFirst().get();
            WorkerStreamInfo workerStreamInfo = new WorkerStreamInfo();
            workerStreamInfo.setProcessId(result.get("processId").asInt());
            workerStreamInfo.setProcessName(processName);
            workerStreamInfo.setSuccess("SUCCESS".equals(result.get("processState").asText()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            workerStreamInfo.setProcessStartTime(simpleDateFormat.parse(result.get("processStartTime").asText()));
            workerStreamInfo.setProcessEndTime(simpleDateFormat.parse(result.get("processEndTime").asText()));
            workerStreamInfo.setProjectCode(projectCode);
            workerStreamInfo.setProjectName(result.get("projectName").asText());
            workStreamListener.apply(workerStreamInfo);
        }
    }

    private String getWorkName(String processName) {
        char[] chars = processName.toCharArray();
        int pos = chars.length;
        int count = 2;
        while (count == 0 || pos > 0) {
            if (chars[pos] == '-') {
                count--;
            }
            pos--;
        }
        return processName.substring(0, pos);
    }
}
