package util.study.thread.jdk8.juc.product.lock;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/14 13:05
 * @File : Clerk
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lock() 显式锁处理生产者消费者问题（线程通信）
 * 生产者消费者问题(线程通信)：店员
 *
 *    显式锁创建：
 *       private Lock lock = new ReentrantLock();
 *    等待/唤醒的条件类创建：
 *       private Condition condition=lock.newCondition();
 *    显式锁的条件类使用： 用来实现线程通信.
 *         Condition类和Object类对比
 *         await = wait
 *         signal = notify
 *         signalAll = notifyAll
 *       condition.await()  condition.signal()   condition.signalAll()
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/14 13:05
 */
public class ClerkLock {
    /**
     * 商品数量
     */
    private  int product;

    /**
     * 显式锁
     */
    private Lock lock = new ReentrantLock();
    /**
     * 显式锁的条件类实例。 用来实现线程通信.
     * Condition类和Object类对比
     * await = wait
     * signal = notify
     * signalAll = notifyAll
     */
    private Condition condition = lock.newCondition();

    public ClerkLock() {
        System.out.println("clerk父类");
    }

    public ClerkLock(int product) {
        this.product = product;
    }

    /**
     * 进货
     */
    public  void addProduct(){
        /**
         * 加锁
         */
        lock.lock();
        try {
            /**
             * 问题2的解决办法：把if条件改为while。即把总是要把wait放在循环中。让他醒了之后立即再去判断一下条件是否满足。防止虚假唤醒
             */
            while(product >= 1){
                System.out.println("商品已满");
                try {
                    /**
                     * 货物满了，等待消费一个。线程等待。等待notify来唤醒
                     * 问题2.注意：这里可能发生“虚假唤醒”：
                     *      就是两个线程同时wait在这里。此时商品是1.  然后们同时被唤醒notifyAll。然后各自给共享资源加了1
                     *      这时候就有3个了。超出期望的上限了。
                     *      解决办法：就是把wait放在循环里面。让他被唤醒之后，重新再去判断一下条件是否满足。满足条件就就继续wait。
                     */
//                    this.wait();  //用了lock就不能用this.wait了，需要用Condition类了
                    condition.await();  // await = wait
                } catch (InterruptedException e) {
    //                e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+(++product));
            /**
             * 已经补货了了。唤醒等待的线程可以来消费了
             * 1.注意：这里没有加else(问题2虚假唤醒中已经把if改成了while。这里肯定不会再有else)。因为线程run里面是有循环次数的，因为防止最后一次循环之后，某个线程到最后一直等待，没有其他线程来唤醒它，程序无法结束。
             */
//            this.notifyAll();
            condition.signalAll();  //signalAll = notifyAll
        } finally {
            /**
             * 解锁
             */
            lock.unlock();
        }

    }

    public  void sale(){
        lock.lock();
        try {
            /**
             * 问题2的解决办法：把if条件改为while。即把wait放在循环中。让他醒了之后立即再去判断一下条件是否满足。防止虚假唤醒
             */
            while(product <= 0){
                System.out.println("缺货");
                try {
                    /**
                     * 缺货。暂停消费。  等待补货，然后唤醒
                     * 问题2.注意：这里可能发生“虚假唤醒”：
                     *     就是两个线程同时wait在这里。此时商品是1.  然后们同时被唤醒notifyAll。然后各自给共享资源加了1
                     *     这时候就有3个了。超出期望的上限了。
                     *     解决办法：就是把wait放在循环里面。让他被唤醒之后，重新再去判断一下条件是否满足。满足条件就就继续wait。
                     */
//                    this.wait();  //用了lock就不能用this.wait了，需要用Condition类了
                    condition.await();  // await = wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+(--product));
            /**
             * 已经消费了一个产品了。通知来补货
             * 1.注意：这里没有加else(问题2虚假唤醒中已经把if改成了while。这里肯定不会再有else)。因为线程run里面是有循环次数的，因为防止最后一次循环之后，某个线程到最后一直等待，没有其他线程来唤醒它，程序无法结束。
             */
//            this.notifyAll();
            condition.signalAll();  //signalAll = notifyAll
        } finally {
            lock.unlock();
        }
    }
}


