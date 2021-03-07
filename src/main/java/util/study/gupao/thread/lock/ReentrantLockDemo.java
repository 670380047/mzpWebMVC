package util.study.gupao.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock(重入锁)。
 *      重入锁的意思就是：在已经持有这把锁的时候，如果再去申请这把锁的话，可以直接重入(增加以下重入的次数)，而不需要竞争
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/5 15:29
 * @File : ReentrantLockDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ReentrantLockDemo {
    //定义一个共享变量
    private static int count = 0;

    //定义一个重入锁
    static Lock lock = new ReentrantLock();

    // 这是一个增加的方法
    private static void increase(){
        /**
         * 开启锁（互斥锁）.
         *
         * 线程A来获取到这把锁。 state = 1  （重入锁的次数等于1）
         */
        lock.lock();
        try {
            /**
             * 假设这个count++是业务逻辑
             */
            count++;
            /**
             * 这里方法里面还是同样的lock。 用的是同一个重入锁对象。
             * 因此线程A进去之后，不需要竞争，只会增加以下重入的次数。这个次数会随着每次的释放而递减。
             */
//            decrease();  // 如果开启这一行来做减法的话。最终结果是0. 因为加和减抵消了。
        } finally {
            /**
             * 释放锁。锁不会自动释放，需要我们手动释放。
             *
             * 随后释放掉这把重入锁，重入次数等于 state = 1-1 =0;
             */
            lock.unlock();
        }
    }

    //这是一个减少的方法
    private static void decrease() {
        /**
         * 同一把重入锁。
         *
         * 线程A再次需要获取锁，这是不需要竞争，只需要增加以下重入次数就好了。
         *      此时state = 1+1 = 2
         */
        lock.lock();
        try {
            count--;
        } finally {
            /**
             * 手动释放锁。
             *
             *  释放一次，重入的次数就减少1. 此时 state = 2-1 = 1
             */
            lock.unlock();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(() -> {
                ReentrantLockDemo.increase();
            }).start();
        }
        Thread.sleep(1000);// 休息这1秒是为了保证上面的1000条线程能够执行完
        System.out.println("result:"+count); //保证原子性。正确结果是1000;
    }
}
