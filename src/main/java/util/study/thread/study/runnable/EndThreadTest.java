package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 11:29
 * @File : EndThreadTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 通知方式：
 * 结束线程测试类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 11:29
 */
public class EndThreadTest {
    public static void main(String[] args) {
        EndThread endThread = new EndThread();
        Thread t1 = new Thread(endThread);
        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            /**
             * 睡眠的异常不做处理。
             * 他是当调用 interrupt()中断阻塞状态的时候，是通过抛出一个InterruptedException异常来实现的。
             * （这个异常不作处理，控制台就不打印这个消息了，看着扎眼）
             * java.lang.InterruptedException: sleep interrupted
             */
//            e.printStackTrace();  // 这个线程通常是不用处理的，他是用来唤醒睡眠的线程的
        }
//        endThread.flag = false;  // 两种调用方式都可以
        endThread.setFlag(false); // 两种调用方式都可以
        System.out.println("程序结束");
    }
}
