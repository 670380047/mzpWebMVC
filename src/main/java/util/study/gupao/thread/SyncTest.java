package util.study.gupao.thread;

import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized关键字。
 *      锁的作用范围：跟对象的生命周期有关，比如对象锁，如果是在多个线程中，调用的都是同一个对象的方法，那么他们就会互斥
 *              如果是类锁，那么在多个线程中不管调用的是不是同一个对象的方法，他们全部都会互斥。
 *      对象锁：创建了一个对象用来充当同步锁。比如：synchronized关键字修饰的代码块的小括号中的对象，或者synchronized关键字修饰的非静态方法。
 *              此时根据线程中使用的对象是否是同一个对象，来判断是否互斥。 如果同一个对象就互斥
 *      类锁：用类.class来充当同步锁。比如：synchronized关键字修饰的代码块的小括号中的对象用“类名.class”充当，或者synchronized关键字修饰的静态方法。
 *              这时，就算在调用的过程中（比如在同步代码快中），使用的不是同一个对象的方法，那也会产生互斥。因为同步代码块被一个类锁锁住了。
 *              这个类的声明周期比对象长的多。
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/1/25 14:03
 * @File : SyncTest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SyncTest {
    /**
     * 定义一个对线。用来作为“同步锁”来使用（属于对象锁）
     */
    Object sync = new Object();

    public static void main(String[] args) throws InterruptedException {
        /**
         * 因为下面两个线程用到的都是这同一个对象（这个syncTest）对象。
         * 因为我们两个lambda中是用syncTest对象去调用的方法，所以不管是用syncTest这个对象中的
         * “同步方法”还是“代码块”，他们都会产生互斥。
         *      同步方法：就是synchronized修饰的方法。如果是同一个对象的同步方法，那么就只能同时被一个线程访问。
         *      同步代码块：就是synchronized（同步锁）修饰的代码块。在这里因为同步锁是属于同一个对象的（就是syncTest这个对象）创建出来的一个属性，
         *          所以也是互斥的。
         */
        SyncTest syncTest = new SyncTest();
        new Thread(()->syncTest.demo2()     //myThread-1和myThread-2会互斥
                ,"myThread-1").start();
        new Thread(()->syncTest.demo2()     //myThread-1和myThread-2会互斥
                ,"myThread-2").start();

        /**
         * 这里与上面不同。
         * 这里的两个进程，分别在lambda中分别使用了两个对象（syncTest3和syncTest4）去调用各自的方法。
         * 因此在“对象锁”（demo1和demo2）的情况下，他们不会产生互斥。 但是在类锁（demo3就是类锁）的情况下，依旧会互斥
         */
        SyncTest syncTest3 = new SyncTest();
        SyncTest syncTest4 = new SyncTest();
        new Thread(()->syncTest3.demo3()    //myThread-3和myThread-4会互斥
                ,"myThread-3").start();
        new Thread(()->syncTest4.demo3()    //myThread-3和myThread-4会互斥
                ,"myThread-4").start();

        Thread.sleep(10);  //为了保证打印结果好看。 休息10毫秒，保证其他线程先跑完
        System.out.println("打印syncTest3对象的布局信息（没有锁）：");
        System.out.println(ClassLayout.parseInstance(syncTest3).toPrintable());
        System.out.println("main线程运行结束");
    }

    /**
     * （对象锁）
     *
     * 修饰实例方法（也就是所说的同步方法）。想要进入这个方法需要获得“当前实例”的锁
     */
    public synchronized void demo1(){
        //临界区
        System.out.println(Thread.currentThread().getName()+":我是修饰实例方法的锁");
    }

    public void demo2(){
        System.out.println(Thread.currentThread().getName()+"准备进入临界区");
        /**
         * （对象锁）
         * 这里是要获取到sync这个对象锁，就能进去。（对象锁）
         */
        synchronized (sync){
            //临界区
            System.out.println(Thread.currentThread().getName()+":我是修饰代码块的锁");
            System.out.println("打印对象的布局信息（有锁）：");  //sync这个对象是“同步锁”
            System.out.println(ClassLayout.parseInstance(sync).toPrintable());
        }
    }

    public void demo3(){
        System.out.println(Thread.currentThread().getName()+"准备进入静态同步代码块临界区");
        /**
         * （类锁）
         * 这里是使用类锁
         */
        synchronized (SyncTest.class){
            //临界区
            System.out.println(Thread.currentThread().getName()+":我是修饰代码块的锁");
        }
    }
}
