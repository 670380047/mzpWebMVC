package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 12:31
 * @File : WaitNotifyRun
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 线程通信实现类。
 * 线程通信（交互）： 当多个线程在完成某些任务时，多个线程之间也需要一定通信，及线程通信。
 *
 * 场景：模拟银行账户，用户A不断我那个账户里面存钱，最多存10000元，当存满是，通知用户B取钱。
 *      用户B不断的从该账户中取钱，当余额不足是，需要通知用户A存钱。
 *
 * 1. wait(): hi当前线程进入等待状态。
 * 2. notify()/notifyAll(): 唤醒一个/所有当前监视器下（锁）处于等待状态的线程。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 12:31
 */
public class WaitNotifyRun implements Runnable{
    int a = 0;

    /**
     * 同步监视器，静态变量。  也可以直接使用WaitNotifyRun.class。
     * 主要就是为了保证多个线程的同步监视器是同一个引用。唯一的
     */
    Object object = new Object();
    Set set = new HashSet();

    @Override
    public void run() {
        while (true) {
            /**
             * ??????
             * 这里有个疑问，为什么这里只能用this呢？  静态变量和类名.class不行呢？
             * 答案：
             *   不是不行。是都可以。
             *   用的锁是哪个对象，那么在下面调用notify() 或者 wait()方法的时候，就要用这个对象
             */
            synchronized (object) {
//                WaitNotifyRun.class.notify();
                object.notify();
//                notify();   //  notify() 同  this.notify()
                if (a < 100) {
                    set.add(a);
                    System.out.println(Thread.currentThread().getName() + ":" + a++);
                }else{
                    System.out.println(Thread.currentThread().getName()+":set="+set.size());
                    break;
                }
                try {
                    /**
                     * wait()方法：等待的同时，释放锁。
                     */
//                    WaitNotifyRun.class.wait();
                    object.wait();
//                    this.wait();
                } catch (InterruptedException e) {
                    // 这个异常时用来唤醒阻塞的。不需要处理
//                    e.printStackTrace();
                }
            }
        }
    }
}
