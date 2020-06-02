package util.study.thread.study.thread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 9:49
 * @File : TestThread
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 1.什么是程序？为了完成某项特定的任务，使用某种语言，编写了一组指令的集合。
 * 2.什么是进程？就是一个正在进行的程序
 * 3.什么是线程？在一个进程中，执行一套功能流程，成为线程。
 *            在一个进程中，执行的多套工能流程，成为多线程。
 * 4.抢占式策略系统：系统会分配给每个执行任务的线程一个很小的时间段用于执行任务，当该时间段用完后，
 *            系统会自动的剥夺其cpu的使用权，交给其他线程去使用。
 *      4.1 以单核CPU为例，单线程和多线程基本没有区别，甚至多线程更慢（把一个任务分成多块，多了cpu切换时间）
 *      4.2 多线程的优点：提高计算机系统对CPU的利用率。
 * 5. JVM本身是多线程的。main方法称为主线程，gc 也在并发执行。
 *
 * 6. 创建线程的方式一：
 *      6.1 声明一个类继承Thread类
 *      6.2 重写run方法，同时编写线程执行体，也就是run方法的方法体
 *      6.3 创建该类的实例
 *      6.4 用该实例调用start()方法启动线程，默认执行run()方法
 * 7. 线程常用方法：--- WindowTest类中
 *      7.1 currentThread(): 获取当前执行线程
 *      7.2 getName(): 获取线程名称
 *      7.3 start(): 启动线程。通知“线程规划器”，该线程处于就绪状态，等待CPU调用
 *      7.4 setName(String name): 给线程设置名字
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 9:49
 */
public class PrimeThreadTest {

    public static void main(String[] args) {
        /**
         * 6.3 创建该类的实例
         */
        PrimeThread primeThread = new PrimeThread();
        /**
         * 6.4 用该实例调用start()方法启动线程，默认执行run()方法
         */
        /**
         * 1.start()方法是通知“线程规划器”，该线程已经准备就绪.
         *  等待调用线程对象的run()方法（这个过程就是让系统安排一个时间来调用Thread中的run()方法）。具有异步执行的效果
         * 2.如果直接调用run()方法，那么此线程对象并不交给“线程规划器”。而是由main主线程来调用run()方法。属于同步执行。
         */
        primeThread.start();
//        primeThread.start();  // 把该线程再启动一次：线程状态异常java.lang.IllegalThreadStateException
        PrimeThread primeThread1 = new PrimeThread();
        primeThread1.start();

        for(int i =100;i<200;i++){
            if(i%2 != 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
