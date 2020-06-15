package util.study.thread.jdk8.juc.product.lock;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/14 13:18
 * @File : TestProducer
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * lock.lock() 显式锁处理生产者消费者问题（线程通信）
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/14 13:18
 */
public class TestProducerLock {
    public static void main(String[] args) {
        /**
         * 店员clerk是共享数据
         */
        ClerkLock clerkLock = new ClerkLock();
        Producer producerA = new Producer(clerkLock);
        Consumer customerA = new Consumer(clerkLock);

        Producer producerB = new Producer(clerkLock);
        Consumer customerB = new Consumer(clerkLock);

        new Thread(producerA,"生产者A").start();
        new Thread(customerA,"消费者A").start();
        new Thread(producerB,"生产者B").start();
        new Thread(customerB,"消费者B").start();
    }
}
