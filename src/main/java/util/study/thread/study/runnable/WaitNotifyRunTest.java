package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 12:42
 * @File : WaitNotifyRunTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程通信测试类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 12:42
 */
public class WaitNotifyRunTest {

    public static void main(String[] args) {
        WaitNotifyRun  waitNotifyRun1 = new WaitNotifyRun();
        Thread t1 = new Thread(waitNotifyRun1,"线程1");
        t1.start();

        Thread t2 = new Thread(waitNotifyRun1,"线程2");
        t2.start();
    }
}
