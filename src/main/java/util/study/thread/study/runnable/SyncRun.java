package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 17:02
 * @File : syncRun
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步实现类
 * 问题：模拟售票程序，实现三个窗口同时访问共享数据时，出现了：无序、重复、超额售票等多线程问题
 * 解决：将多个线程需要访问的共享数据包装起来，确保一次只能有一个线程能访问共享数据
 *
 * java解决办法：
 *   1.同步代码块
 *          synchronized(同步监视器)｛
 *              // 需要访问的共享数据
 *          ｝
 *        其中的“同步监视器”:俗称“锁”。可以使用任意对象充当，但是必须确保多个线程持有通一把锁（同一个对象）。
 *   2.同步方法:
 *          在返回值前面加上synchronized关键字。-----隐式的锁：this（非静态方法）  类（静态方法）
 *          public synchronized void show(){
 *              // 需要访问的共享数据
 *          }
 *   3.同步锁：
 *      3.1 创建一个ReentrantLock锁的实例。
 *          Lock lock = new ReentrantLock();
 *      3.2 手动加锁
 *          lock.lock();
 *      3.3 手动解锁。解锁最好放在finally中
 *          lock.unlock();
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 17:02
 */
public class SyncRun implements Runnable{
    int tick = 100;
    /**
     * 方法一： 1.创建一个对象，用来充当同步锁。
     */
    Object obj = new Object();

    /**
     * 方法三：同步锁。 1.创建一个ReentrantLock锁的实例。
     */
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        /**
         * 方法一：同步代码块
         * synchronized (同步监视器)｛访问的共享数据｝
         */
        while (true){
            /**
             * 方法一：2.用obj对象充当“同步监视器”（锁）。 这里也可以使用this：因为继承Runnable接口，在外部我们只实例化了一个对象syncRun，
             * 然后把这一个对象分别给了好几个Thread线程，这些线程操作的还是同一个对象syncRun。所以这里的this表示当前对象，也能确保
             * 多个线程使用同一把锁。
             */
            synchronized (this){
                if(tick > 0){
                    System.out.println(Thread.currentThread().getName()+"完成售票，余票为"+ --tick);
                }
            }
        }

        /**
         * 方法二：调用“同步方法”
         */
//        while (true){
//            show();
//        }


//        while(true){
//            /**
//             * 方法三：同步锁。
//             * 2. 手动上锁
//             */
//            lock.lock();
//            try {
//                if(tick > 0){
//                    System.out.println(Thread.currentThread().getName()+"完成售票，余票为"+ --tick);
//                }
//            } finally {
//                /**
//                 * 方法三：同步锁。
//                 * 3. 手动解锁。
//                 * 注意，必须要解锁，否则就一直锁着。所以这里放在finally里面，保证解锁一定会执行
//                 *
//                 */
//                lock.unlock();
//            }
//        }
    }

    /**
     * 方法二：实现同步方法
     *      在返回值前面加上synchronized关键字。-----隐式的锁：this
     */
    public synchronized void show(){
        if(tick > 0){
            System.out.println(Thread.currentThread().getName()+"完成售票，余票为"+ --tick);
        }
    }
}
