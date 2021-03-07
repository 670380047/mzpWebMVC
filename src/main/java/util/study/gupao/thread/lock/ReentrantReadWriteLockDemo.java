package util.study.gupao.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 重入读写锁：ReentrantReadWriteLock
 *      读和读不互斥  （保证多个线程可以同时读统一份数据。毕竟读不会对数据产生修改，可以同时发生）
 *      读和写互斥
 *      写和写互斥
 *
 *      适用于在“读多写少”的情况下。
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/5 15:57
 * @File : ReentrantReadWriteLockDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ReentrantReadWriteLockDemo {
    //定义一个map来充当缓存。
    static Map<String, Object> cacheMap = new HashMap<>();
    /**
     * 创建一个读写重入锁
     */
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    /**
     * 用读写重入锁创建一个读锁
     */
    static Lock readLock = rwl.readLock();
    /**
     * 用读写重入锁创建一个写锁
     */
    static Lock writeLock = rwl.writeLock();

    //get方法
    public static Object get(String key) {
        /**
         * 开启读锁。 读和读之间不互斥。 多个读线程可以同时进行
         */
        readLock.lock();
        try {
            return cacheMap.get(key);
        } finally {
            /**
             * 释放读锁
             */
            readLock.unlock();
        }
    }

    //put方法
    private static Object put(String key, Object value) {
        /**
         * 开启写锁。 写和读互斥、写和写也互斥
         */
        writeLock.lock();
        try {
            return cacheMap.put(key, value);
        } finally {
            /**
             * 释放写锁
             */
            writeLock.unlock();
        }
    }


}
