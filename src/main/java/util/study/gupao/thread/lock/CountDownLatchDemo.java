package util.study.gupao.thread.lock;

import java.util.concurrent.CountDownLatch;

/**
 *  CountDownLatch的使用demo。 是AQS的一种共享锁
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/10 17:12
 * @File : CountDownLanchDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        /**
         * 初始化一个CountDownLatch对象，并且设置计数为3。
         *      这里的 3 就保存在AQS中的state变量里。
         */
        CountDownLatch countDownLatch = new CountDownLatch(3);
        /**
         * 这里创建了3个线程。
         *      然后每个线程内部都调用了 countDownLatch.await()来阻塞线程。等待计数为0之后在执行。
         */
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" is begin....");
                try {
                    /**
                     *  调用了 countDownLatch.await()来阻塞线程。
                     */
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+" is end.");
            }).start();
        }

        System.out.println("当前的countDownLatch数值等于： "+countDownLatch.getCount() );

        /**
         * 在主线程中计数。每调用一次countDownLatch.countDown()，AQS中的state的个数都会减少1。
         *      这里模仿3个线程调用3次之后，上面因为调用countDownLatch.await()阻塞的3个线程都会
         *      恢复过来，继续执行了。
         */
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.countDown();
        System.out.println("main线程结束！");

    }
}
