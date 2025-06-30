package net.github.fast.distributed.lock.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <P><B>分布式锁:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月09日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {


    /**
     * 分布式锁的key
     */
    String value();

    /**
     * 尝试获取锁的时间，超过则报错
     * 单位s
     */
    int time() default 0;
}
