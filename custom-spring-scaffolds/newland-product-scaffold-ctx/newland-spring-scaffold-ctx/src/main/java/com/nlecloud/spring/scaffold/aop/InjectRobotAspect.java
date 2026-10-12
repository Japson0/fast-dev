package com.nlecloud.spring.scaffold.aop;

import com.nlecloud.spring.scaffold.annotation.InjectRobot;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import com.nlecloud.spring.annotation.UserInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class InjectRobotAspect {

    @Pointcut("@annotation(com.nlecloud.spring.scaffold.annotation.InjectRobot)")
    public void myCustomAnnotationCut() {}

    // 环绕通知
    @Around("myCustomAnnotationCut() && @annotation(injectRobot)")
    public Object doAround(ProceedingJoinPoint joinPoint,InjectRobot injectRobot) throws Throwable {
        UserInfo userInfo = injectRobot.update()
                ? new UserWrapper(injectRobot.userId(), injectRobot.username(), injectRobot.tenantId())
                : UserContext.getRobotUser();
        return UserContext.callWith(userInfo, joinPoint::proceed);
    }
}
