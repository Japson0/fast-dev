package net.github.fast.distributed.lock.zk;

import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * <P><B>zk读写锁:</B></P>
 * RevisionTrail:(Date/Author/Description)
 * 2025年06月27日 CREATE
 *
 * @author Japson Huang
 * @version1.0
 */
public class ZookeeperReadWriteLock implements ReadWriteLock {

    private final InterProcessReadWriteLock interProcessReadWriteLock;

    public ZookeeperReadWriteLock(InterProcessReadWriteLock interProcessReadWriteLock) {
        this.interProcessReadWriteLock = interProcessReadWriteLock;

    }

    @Override
    public Lock readLock() {
        return new ZookeeperLock(interProcessReadWriteLock.readLock());
    }

    @Override
    public Lock writeLock() {
        return new ZookeeperLock(interProcessReadWriteLock.writeLock());
    }
}
