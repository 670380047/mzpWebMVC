package util.study.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 15:16
 * @File : TestCountDownlatch
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:闭锁。 在完成某些运算时，需要等待其他线程的运算完成之后，当前运算才开始执行。
 *      步骤：
 *          1.在主线程声明一个闭锁对象。传入的是一个计数器。
 *          2.在主线程实例化一个Runnable接口实例。 这个实例里面有一个CountDownLatch类型的变量，就是用来保存计数器的。
 *              子线程里面每一次执行，都会把计数器-1. 直到变为0。
 *              关键就是保证子线程里面的调用countDown()方法的闭锁对象latch和外面主线程里调用await()方法的latch是同一个对象
 *          3.子线程准备就绪
 *          4.latch.countDown() 在子线程中保证每一次线程运行之后，都把闭锁的计数器-1。
 *          5.闭锁实例latch调用await()方法，来阻塞接下来的所有线程。
 *            直到闭锁的计数器为0后，下面的线程才会被唤醒。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 15:16
 */
public class TestCountDownLatch {
    /**
     * 计算多个线程执行的时间
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 1.在主线程声明一个闭锁对象。传入的是一个计数器。
         *      计数器的作用：比如说某个线程需要被执行多少次，下面的5就是执行5次。
         */
        final CountDownLatch latch = new CountDownLatch(5);
        /**
         * 2.在主线程实例化一个Runnable接口实例。 这个实例里面有一个CountDownLatch类型的变量，就是用来保存计数器的。
         * 子线程里面每一次执行，都会把计数器-1. 直到变为0。
         * 关键就是保证子线程里面的调用countDown()方法的闭锁对象latch和外面主线程里调用await()方法的latch是同一个对象。也就是这里的latch
         */
        LatchDemo latchDemo = new LatchDemo(latch);

        long start = System.currentTimeMillis();
        /**
         * 3.子线程准备就绪
         * 步骤4在线程里面
         */
        for(int i=0;i<5;i++){
            new Thread(latchDemo).start();
        }
        try {
            /**
             * 关键步骤。在闭锁计数器等于0之前，主线程中后面的逻辑都会被阻塞。
             * 5.闭锁实例latch调用await()方法，来阻塞接下来的所有线程。
             *   直到闭锁的计数器为0后，下面的线程才会被唤醒。
             */
            latch.await();
        } catch (InterruptedException e) {
//            e.printStackTrace();  // 线程中断异常。用来唤醒阻塞的线程的。不需要处理
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间："+(end-start)+"ms");
    }
}

class LatchDemo implements Runnable{

    /**
     * 闭锁的计数器
     */
    private CountDownLatch latch;

    public LatchDemo() {
    }

    public LatchDemo(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for(int i=0;i<10000;i++){
                    if(i%2==0){
                        System.out.println(i);
                    }
                }
            } finally {
                /**
                 * 4.latch.countDown() 在子线程中保证每一次线程运行之后，都把闭锁的计数器-1。
                 */
                latch.countDown();
            }
        }
    }
}