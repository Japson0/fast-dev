package net.github.fast.distributed.lock.starter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * <P><B>分布式锁:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public interface DistributedLockHandler {

    /**
     *获取读写锁
     *RevisionTrail:(Date/Author/Description)
     * 2025年06月26日
     *@author Japson Huang
     *
    */
    ReadWriteLock getReadWriteLock(String key);

    /**
     *获取锁
     *RevisionTrail:(Date/Author/Description)
     * 2025年06月26日
     *@author Japson Huang
     * @param key 锁的key
     * @param fair 是否公平锁
    */
    Lock getLock(String key,boolean fair);

}
