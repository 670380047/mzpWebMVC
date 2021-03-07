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
                //睡眠10秒。 在这里会被中断标志（true）给中断了。即：从睡眠阻塞中唤醒过来
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
                 *  因为在捕获异常的时候发生了“线程复位”，所以interrupt又变为了false，那么他就可以继续循环了。因次要跳出while循环的话，
                 *  需要再次手动中断一下。
                 *
                 *  上一个中断是JVM发起的。 现在到了这里，选择权就交给了我们程序自己，
                 *      是继续循环呢（那就啥也不做）
                 *      还是跳出循环（那就再中断一次）
                 */
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("线程处理结束。");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new InterruptTest(),"Thread-test-01");
        t1.start(); //线程就绪

        Thread.sleep(1000); //休眠1秒，保证线程开始之后，再进行interrupt()

        //让当前线程（这里是main线程）中断。通知当前线程一声，等着当前线程去响应吧（在本方法中没做响应）
        Thread.currentThread().interrupt(); //将当前线程的中断标志设置为true。
        System.out.println("Thread.interrupted()第一次:"+Thread.interrupted()); //true。 显示当前线程的中断标志（默认是false）。并且重置中断标志
        System.out.println("Thread.interrupted()第二次:"+Thread.interrupted()); //false。 上一行代码已经重置中断标志了，重置为默认值false了。

        //查看t1线程的中断标志。
        System.out.println("-------------main线程的中断标志："+t1.isInterrupted()); // false. 默认值是false
        /**
         * t1.interrupt()方法就是把t1线程interrupt标志设置为true   （默认值是false）
         */
        t1.interrupt();
        //再次查看t1线程的中断标志
        System.out.println("-------------main线程的中断标志："+t1.isInterrupted()); // true。因为上一行代码通知t1要中断，即把中断标志设置为了true
        //然后我在t1线程里面用sleep响应了本次中断，即唤醒了因sleep而阻塞的代码并且抛出了InterruptedException异常，
        //这个异常被捕获后，会重置t1的中断标志，把标志设置false。 然后如果不管的话，因为我while()循环里面我是用中断标志来作为判断条件的
        //所以捕获异常之后（重置中断标志），我又手动把中断标志位改为了true，为了就是让程序能够跳出while()循环

    }
}
