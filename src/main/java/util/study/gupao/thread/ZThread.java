package util.study.gupao.thread;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 一、synchronized关键字。--》SyncTest 类中
 *      1.对象锁
 *      2.类锁
 *      3.对象打印工具包 jol-core
 * 二、死锁演示和解决. --》DeadLockDemo 类中
 * 三、ThreadLocal使用。--》ThreadLocalTest 类中
 * 四、重入锁。ReentrantLock的使用。 --》ReentrantLockDemo 类中
 * 五、线程交替执行，轮流打印A、B、C。有重入锁实现。 --》TestAbcAlternate 类中
 * 六、重入读写锁：ReentrantReadWriteLock。适用于在“读多写少”的情况下。 --》ReentrantReadWriteLockDemo 类中
 *        读和读不互斥  （保证多个线程可以同时读统一份数据。毕竟读不会对数据产生修改，可以同时发生）
 *        读和写互斥
 *        写和写互斥
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/1/25 14:05
 * @File : ZThread
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ZThread {
    public static void main(String[] args) {
        Object weakWf = new Object();
        Object softWf = new Object();
        Object phantomWf = new Object();


        WeakReference<Object> wf = new WeakReference<Object>(weakWf);
        SoftReference<Object> sf = new SoftReference<Object>(softWf);
        ReferenceQueue req = new ReferenceQueue(); //虚引用必须配上引用队列（ReferenceQueue）一起使用
        PhantomReference<Object> pf = new PhantomReference<>(phantomWf,req);

        //对象置位null
        weakWf = null;
        softWf = null;
        phantomWf = null;

        System.gc(); //通知gc

        System.out.println("弱引用："+wf.get());  //null，
        System.out.println("软引用："+sf.get());  //对象的地址
        System.out.println("虚引用："+pf.get());  //null

    }
}
