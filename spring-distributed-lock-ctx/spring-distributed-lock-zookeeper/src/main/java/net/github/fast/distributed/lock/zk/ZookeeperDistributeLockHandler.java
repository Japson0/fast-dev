package net.github.fast.distributed.lock.zk;

import net.github.fast.distributed.lock.starter.DistributedLockHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * <P><B>zk锁服务:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ZookeeperDistributeLockHandler implements DistributedLockHandler {

    private final CuratorFramework curatorFramework;

    public ZookeeperDistributeLockHandler(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @Override
    public ReadWriteLock getReadWriteLock(String key) {
        return new ZookeeperReadWriteLock(new InterProcessReadWriteLock(curatorFramework,key));
    }
    @Override
    public Lock getLock(String key,boolean fair) {
        //zk默认就是公平锁，非公平锁暂时不支持
        return new ZookeeperLock(new InterProcessMutex(curatorFramework, key));
    }

}
