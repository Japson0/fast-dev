package net.evecom.dolphinscheduler.callback;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <P><B>Description:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2023年08月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@RequestMapping("dolphinscheduler/callback")
public class CallBackController {


    private final WorkStreamCallBack workStreamCallBack;

    public CallBackController(WorkStreamCallBack workStreamCallBack) {
        this.workStreamCallBack = workStreamCallBack;
    }

    @PostMapping
    public void callBack(@RequestBody String callBack) throws Exception {
        try {
            workStreamCallBack.apply(callBack);
        } catch (Exception e) {
            throw new CallBackException(String.format("dolphinscheduler回调失败。回调信息为%s", callBack), e);
        }
    }
}
