package net.github.fast.distributed.lock.zk;

import org.apache.curator.framework.recipes.locks.InterProcessLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <P><B>zk分布式锁:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ZookeeperLock implements Lock {

    private final InterProcessLock interProcessLock;

    public ZookeeperLock(InterProcessLock interProcessLock) {
        this.interProcessLock = interProcessLock;
    }

    @Override
    public void lock() {
        try {
            interProcessLock.acquire();
        } catch (Exception e) {
            throw new RuntimeException("获取ZooKeeper锁失败", e);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        try {
            if (!interProcessLock.acquire(1, TimeUnit.SECONDS)) { // 示例中加个超时，防止永久阻塞
                throw new RuntimeException("获取ZooKeeper锁超时");
            }
        } catch (Exception e) {
            throw new RuntimeException("获取ZooKeeper锁失败", e);
        }
    }

    @Override
    public boolean tryLock() {
        try {
            return interProcessLock.acquire(0, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("尝试获取ZooKeeper锁失败", e);
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            return interProcessLock.acquire(time, unit);
        } catch (Exception e) {
            throw new RuntimeException("尝试获取ZooKeeper锁失败", e);
        }
    }

    @Override
    public void unlock() {
        try {
            interProcessLock.release();
        } catch (Exception e) {
            throw new RuntimeException("释放ZooKeeper锁失败", e);
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("不支持Condition操作");
    }
}
