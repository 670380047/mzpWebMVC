package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 17:04
 * @File : syncRunTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程同步测试类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 17:04
 */
public class SyncRunTest {
    public static void main(String[] args) {
        SyncRun syncRun = new SyncRun();
        Thread t1 = new Thread(syncRun,"1号窗口");
        t1.start();
        Thread t2 = new Thread(syncRun,"2号窗口");
        t2.start();
        Thread t3 = new Thread(syncRun,"3号窗口");
        t3.start();
    }

}
