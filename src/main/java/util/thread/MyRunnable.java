package util.thread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/9/25 21:17
 * @File : MyRunnable
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/9/25 21:17
 */
public class MyRunnable implements Runnable{
    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        //start()方法是通知“线程规划器”，该线程已经准备就绪，
        // 等待调用线程对象的run()方法（这个过程就是让系统安排一个时间来调用Thread中的run()方法）。具有异步执行的效果
        thread.start();
        //如果直接调用run()方法，那么此线程对象并不交给“线程规划器”。而是由main主线程来调用run()方法。属于同步执行。
//        thread.run();
        System.out.println("MyRunnable运行结束！");
    }

    @Override
    public void run() {
        System.out.println("MyRunnable类启动");
    }
}
