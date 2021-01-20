package util.study.thread.study.runnable;

import java.util.concurrent.TimeUnit;

/**
 * mzp：测试interrupt的用法
 *      interrupt是一个中断的标志。 默认是false。
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/1/20 16:52
 * @File : InterruptTest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class InterruptTest implements Runnable{
    @Override
    public void run() {
        /**
         * interrupt是一个中断的标志。 默认是false。
         *
         *  用interrupt来控制线程的循环和终止。
         *  用这种方式控制循环的情况下：捕获异常之后，需要处理异常，比如再次中断一次（调用interrupt()方法），
         *      因为捕获到异常的时候，中断标志会复位，重新变为false，那么他就又可以继续循环了。
         *      但是如果我们在捕获异常之后，再进行一次中断的话，就可以跳出循环了（因为循环检测到中断标志变了，不符合循环条件了）
         */
        while(!Thread.currentThread().isInterrupted()){
            try {
                System.out.println("线程开始休眠.....");
                //睡眠10秒
                TimeUnit.SECONDS.sleep(10);
                System.out.println("线程休眠结束。");
            } catch (InterruptedException e) {
                /**
                 * mzp：重点！ 捕获到这个异常，会触发“线程复位”，就是把interrupt的标志的值重新变为false。
                 *
                 *  这个异常是由JVM层面跑出来的中断请求的异常。
                 */
                e.printStackTrace();
                /**
                 * mzp：处理异常，再次中断一次，以跳出循环。
                 *
                 *  因为在捕获异常的时候发生了“线程复位”，所以interrupt又变为了false，那么他就可以继续循环了。因为要再次中断一下。
                 *
                 *  上一个中断是JVM发起的。 现在到了这里，选择权就交给了我们程序自己，
                 *      是继续循环呢（那就啥也不做）
                 *      还是跳出循环（那就再中断一次）
                 */
                Thread.currentThread().interrupt();
               // Thread.interrupted();  // 这个是手动线程复位。 前面JVM在抛出异常的时候做了个自动“线程复位”
            }
        }
        System.out.println("线程处理结束。");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new InterruptTest(),"Thread-test-01");
        t1.start(); //线程就绪

        Thread.sleep(1000); //休眠1秒，保证线程开始之后，再进行interrupt()
        /**
         * interrupt()方法就是把interrupt设置为true   （默认值是false）
         */
        t1.interrupt();


    }
}
