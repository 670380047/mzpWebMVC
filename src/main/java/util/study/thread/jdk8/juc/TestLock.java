package util.study.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/14 12:18
 * @File : TestLock
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 五、解决多线程安全问题的几种方式：
 *      1.同步代码快
 *      2.同步方法
 *      3.同步锁lock   （jdk5之后。更加灵活，但是如果锁没有释放的话，那风险更大。所以unlock一般放在finally中）
 *          这是一个显式锁，需要通过lock()方法来上锁，也必须通过unlock()方法进行释放锁。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/14 12:18
 */
public class TestLock {
    public static void main(String[] args) {
//        /**
//         * 这里选择采用实现Callable接口来创建线程。（为了熟悉这种方式：有返回值、可以抛出异常）
//         * 1.创建一个Callable实现类的实例
//         */
//        Ticket ticketCall = new Ticket();
//        /**
//         * 2.创建一个FutureTask的实例来包装Callable接口的实现类的实例。
//         */
//        FutureTask<Integer> futureTask = new FutureTask<>(ticketCall);
//        /**
//         * 3.同Runnable接口一样，需要放入Thread类中。
//         *  这里入参依旧是Runnable类型。刚好FutureTask就是Runnable的实现类（孙子类）
//         */
//        Thread t1 = new Thread(futureTask,"1号窗口");
//        Thread t2 = new Thread(futureTask,"2号窗口");
//        Thread t3 = new Thread(futureTask,"3号窗口");
//        t1.start();
//        t2.start();
//        t3.start();
//        try {
//            System.out.println("结果："+futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        Ticket2 ticket2 = new Ticket2();
        Thread t4 = new Thread(ticket2,"4号窗口");
        Thread t5 = new Thread(ticket2,"5号窗口");
        Thread t6 = new Thread(ticket2,"6号窗口");
        t4.start();
        t5.start();
        t6.start();
    }
}

/**
 * 疑问？ 这里实现Callable接口为什么没有加锁，但是也没有出现线程安全问题呢？
 */
class Ticket implements Callable<Integer>{
    private int ticket = 100;

    @Override
    public Integer call() throws Exception {
        while(true){
            if(ticket > 0){
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"：完成售票，余票为："+(--ticket));
            }else {
                break;
            }
        }
        return ticket;
    }
}


class Ticket2 implements Runnable{
    private int ticket = 100;

    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            /**
             * 加锁
             */
            lock.lock();
            try {
                if(ticket > 0){
                    //Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName()+"：完成售票，余票为："+(--ticket));
                }else{
                    break;
                }
            } finally {
                /**
                 * 解锁
                 */
                lock.unlock();
            }

        }
    }
}