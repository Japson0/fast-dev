package net.github.fast.distributed.lock.redis;
import net.github.fast.distributed.lock.starter.DistributedLockHandler;
import org.redisson.api.RedissonClient;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * <P><B>reids分布式锁:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月23日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class RedisDistributeLockHandler implements DistributedLockHandler {

    private final RedissonClient redissonClient;

    public RedisDistributeLockHandler(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    @Override
    public ReadWriteLock getReadWriteLock(String key) {
        return redissonClient.getReadWriteLock(key);
    }

    @Override
    public Lock getLock(String key,boolean fair) {

        return fair?redissonClient.getFairLock(key):redissonClient.getLock(key);
    }

}
