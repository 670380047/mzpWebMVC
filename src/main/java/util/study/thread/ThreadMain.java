package util.study.thread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/9/25 19:46
 * @File : ThreadMain
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/9/25 19:46
 */
public class ThreadMain {
    public static void main(String[] args) {
        System.out.println("当前线程名（name）："+Thread.currentThread().getName()+"。这个main线程的名字和main()方法只是名字形同而已，并不是一回事："
                );
        MyThread myThread = new MyThread("ellia");
        //start()方法是通知“线程规划器”，该线程已经准备就绪，
        // 等待调用线程对象的run()方法（这个过程就是让系统安排一个时间来调用Thread中的run()方法）。具有异步执行的效果
        myThread.start();
        //如果直接调用run()方法，那么此线程对象并不交给“线程规划器”。而是由main主线程来调用run()方法。属于同步执行。
//        myThread.run();
        ThreadMain.threadTest();
        System.out.println("运行结束");
    }

    public static void threadTest(){
        try {
            for(int i =0;i<5;i++){
                int time = (int) (Math.random()*2000);
                Thread.sleep(time);
                System.out.println("run="+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
