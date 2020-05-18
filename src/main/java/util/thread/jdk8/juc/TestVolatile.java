package util.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/11 10:45
 * @File : TestVolatile
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 测试volatile关键字。
 *
 * volatile关键字：禁止指令重排序。当多线线程进行操作共享数据时，可以保证内存中的数据时可见的。
 *      volatile原理：volatile 变量在每次被线程访问时，都强迫从主内存中重读该变量的值，而当该变量发生变化时，又会强迫线程将最新的值刷新到主内存，
 *      这样任何时刻，不同的线程总能看到该变量的最新值。
 *      底层：Volatile实现内存可见性是通过store和load指令完成的；也就是对volatile变量执行写操作时，会在写操作后加入一条store指令，
 *      即强迫线程将最新的值刷新到主内存中；而在读操作时，会加入一条load指令，即强迫从主内存中读入变量的值。
 *      但volatile不保证volatile变量的原子性
 *
 *
 *      线程写volatile变量的过程:
 *          改变线程工作内存中volatile变量副本的值
 *          将改变后的副本的值从工作内存刷新到主内存
 *
 *      线程读volatile变量的过程:
 *          从主内存中读取volatile变量的最新值到线程的工作内存中
 *          从工作内存中读取volatile变量的副本
 *
 * JVM的内存模型：程序启动后，jvm会为每一个线程，提供一个独立的缓存（工作内存，存放主内存的副本），用于提高效率。每个线程都将主内存中的数据
 *     拷贝一份放入自己的工作内存中，该线程通过工作内存来与主内存进行交互（线程不能直接与主内存交互），线程对数据的一些修改操作也是现在自己的
 *     工作内存中修改完后，再写入主内存中。
 *     ·存在的问题：多线程根据执行顺序的不同可能会造成线程之间读取的数据不是主内存中最新的，而是自己缓存（工作内存）中的。
 *     ·内存可见性：一个线程对共享变量值的修改，能够及时被其他线程看到则称这个共享变量在线程之间是可见的
 *
 *     ·不满足  “内存可见性时”  会引发的问题：当多个线程操作共享数据时，彼此不可见，造成读取的数据不是主内存中最新的。
 *     ·解决办法：
 *          1.用同步锁 synchronized (共享数据){处理逻辑...}。但是效率很低，每次都要判断锁是否在用。
 *              JVM中synchronized的两条规定：
 *              1.1.线程解锁前，必须把共享变量的最新值刷新到主内存中
 *              1.2.线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时，需要从主内存中重新读取最新的值（注意：加锁与解锁需要是同一把锁）
 *                  （而且其他线程T再去申请同一个锁的话，T线程中原来的工作内存会失效，然后重新读取主内存）
 *
 *          2.volatile 变量在每次被线程访问时，都强迫从主内存中重读该变量的值，而当该变量发生变化时，又会强迫线程将最新的值刷新到主内存，
 *            这样任何时刻，不同的线程总能看到该变量的最新值。
 *    volatile和 synchronized 对比
 *          1.volatile 是轻量级的同步策略
 *          2.volatile 不具备“互斥性”
 *          3.volatile 不能保证变量的“原子性”
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/11 10:45
 */
public class TestVolatile {
    public static void main(String[] args) {

        ThreadDemo1 runnable1 = new ThreadDemo1();
//        new Thread(runnable).start();   //Runnable接口还需要用 Thread实例化一下，才能使用。
        Thread thread = new Thread(runnable1); //Runnable接口还需要用 Thread实例化一下，才能使用。
        thread.start();

        while(true){
            /**
             * synchronized 保证每次都能刷新缓存。解决内存可见性引发的问题。
             */
            synchronized (runnable1){
                if(runnable1.isSynchronized()){
                    System.out.println("synchronized关键字处理完成：-----------  isSynchronized="+runnable1.isSynchronized());
                    break;
                }
            }
        }

        ThreadDemo2 runnable2 = new ThreadDemo2();
//        new Thread(runnable2).start();   //Runnable接口还需要用 Thread实例化一下，才能使用。
        Thread thread2 = new Thread(runnable2); //Runnable接口还需要用 Thread实例化一下，才能使用。
        thread2.start();
        while(true){
            if(runnable2.isVolatile()){
                System.out.println("volatile关键字处理完成：-----------  isVolatile="+runnable2.isVolatile());
                break;
            }
        }
    }
}

/**
 * 测试synchronized关键字的用法
 */
class ThreadDemo1 implements Runnable {

    private boolean isSynchronized = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isSynchronized = true;
        System.out.println(Thread.currentThread().getName() + "执行完毕。isSynchronized=" + isSynchronized());

    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public void setSynchronized(boolean aSynchronized) {
        isSynchronized = aSynchronized;
    }
}

/**
 * 测试volatile关键字的用法
 */
class ThreadDemo2 implements Runnable {

    /**
     * 使用 volatile 关键字修饰共享变量
     */
        private volatile boolean isVolatile = false;

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isVolatile = true;
            System.out.println(Thread.currentThread().getName() + "执行完毕。isVolatile=" + isVolatile());
        }

        public boolean isVolatile() {
            return isVolatile;
        }

        public void setVolatile(boolean aVolatile) {
            isVolatile = aVolatile;
        }

}