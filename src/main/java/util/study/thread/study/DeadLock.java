package util.study.thread.study;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 11:13
 * @File : DeadLock
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程死锁，例子
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 11:13
 */
public class DeadLock {
    public static void main(String[] args) {
        Lock lock1 = new Lock();
        Thread t1 = new Thread(lock1);
        t1.start();

        Lock lock2 = new Lock();
        Thread t2 = new Thread(lock2);
        lock2.flag = false;
        t2.start();
    }
}

class Lock implements Runnable{
   static Object obj1 = new Object(); // 共享资源1。 因为外部定义了两个实例用。所以这里定义为static，否则不能共享
   static Object obj2 = new Object(); // 共享资源2。 因为外部定义了两个实例用。所以这里定义为static，否则不能共享

    boolean flag = true;

    @Override
    public void run() {
        if(flag){
            synchronized (obj1){
                System.out.println("获取资源1，等待资源2.....");
                synchronized (obj2){
                    System.out.println("********成功运行************");
                }
            }
        }else{
            synchronized (obj2){
                System.out.println("获取资源2，等待资源1.....");
                synchronized (obj1){
                    System.out.println("----------成功运行------------------");
                }
            }
        }
    }
}
