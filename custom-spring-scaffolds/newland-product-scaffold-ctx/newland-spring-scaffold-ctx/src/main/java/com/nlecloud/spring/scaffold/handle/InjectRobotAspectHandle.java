package com.nlecloud.spring.scaffold.handle;

import com.nlecloud.spring.scaffold.annotation.InjectRobot;
import com.nlecloud.spring.scaffold.common.UserContext;
import com.nlecloud.spring.scaffold.common.UserWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Collections;

@Aspect
public class InjectRobotAspectHandle {

    @Pointcut("@annotation(com.nlecloud.spring.scaffold.annotation.InjectRobot)")
    public void myCustomAnnotationCut() {}

    // 环绕通知
    @Around("myCustomAnnotationCut() && @annotation(injectRobot)")
    public Object doAround(ProceedingJoinPoint joinPoint,InjectRobot injectRobot) throws Throwable {
        try {
            if(injectRobot.update()){
                UserContext.setUserInfo(new UserWrapper(injectRobot.userId(), injectRobot.username(), injectRobot.tenantId()));
            }else{
                UserContext.setUserInfo(UserContext.getRobotUser());
            }
            // 执行原方法
            Object result = joinPoint.proceed();

            return result;
        }finally {
            UserContext.clean();
        }
    }
}