package com.zhang.redis.cache;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {


    RedissonClient redissonClient;

    public RedisLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 互斥锁，seconds秒后自动失效
     * @param key
     * @param seconds
     */
    public boolean lock(String key, int seconds) {
        RLock rLock = redissonClient.getLock(key);
        if (rLock.isLocked()) {
            return false;
        }
        // 讲错了一个地方，这个方法，lock里面传入的参数，不是加锁的阻塞等待的时间
        // 加锁之后，如果在指定的秒，1秒里，没释放锁，他会自动再底层把锁释放掉这样子
        rLock.lock(seconds, TimeUnit.SECONDS);
        return true;
    }

    /**
     * 互斥锁，自动续期
     *
     * @param key
     */
    public boolean lock(String key) {
        RLock rLock = redissonClient.getLock(key);
        if (rLock.isLocked()) {
            return false;
        }
        rLock.lock();
        return true;
    }

    public boolean blockedLock(String key) {
        RLock rLock = redissonClient.getLock(key);
        rLock.lock();
        return true;
    }

    public boolean tryLock(String key, long timeout) throws InterruptedException {
        RLock rLock = redissonClient.getLock(key);
        return rLock.tryLock(timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 手动释放锁
     *
     * @param key
     */
    public void unlock(String key) {
        RLock rLock = redissonClient.getLock(key);
        if (rLock.isLocked()) {
            rLock.unlock();
        }
    }

}
