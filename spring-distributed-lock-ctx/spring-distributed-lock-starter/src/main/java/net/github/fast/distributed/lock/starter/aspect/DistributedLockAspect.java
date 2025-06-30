package net.github.fast.distributed.lock.starter.aspect;

import net.github.fast.distributed.lock.starter.DistributedLockHandler;
import net.github.fast.distributed.lock.starter.annotation.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Aspect
public class DistributedLockAspect {

    private final DistributedLockHandler distributedLockHandler;

    public DistributedLockAspect(DistributedLockHandler distributedLockHandler) {
        this.distributedLockHandler = distributedLockHandler;
    }

    @Around("@annotation(net.github.fast.distributed.lock.starter.annotation.DistributedLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DistributedLock lockAnnotation = method.getAnnotation(DistributedLock.class);

        String rawKey = lockAnnotation.value();
        Lock lock = distributedLockHandler.getLock(rawKey,false);

        boolean isLocked = true;
        try {
            if(lockAnnotation.time()>0){
                isLocked=lock.tryLock(lockAnnotation.time(), TimeUnit.SECONDS);
            }else{
                lock.lock();
            }
            return joinPoint.proceed(); // 执行目标方法

        } finally {
            if (isLocked) {
                lock.unlock();
            }
        }
    }
}