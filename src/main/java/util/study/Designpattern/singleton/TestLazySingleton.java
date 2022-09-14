package util.study.Designpattern.singleton;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 16:14
 * @File : LazySingleton
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 单例模式之懒汉模式：（懒汉模式：延迟加载。即：调用的时候才去创建对象）
 *      步骤：1.定义一个静态变量。必须是静态的，为了保证作用域是全局的，是属于类级别的，不是对象级别的。
 *           2.私有化构造函数。不允许外部方法来实例化
 *           3.创建一个public 静态方法，共外部调用和创建实例。
 *                  在这一步可以做一些逻辑保证线程安全
 * 一、懒汉模式
 * 二、线程安全的懒汉模式(双重校验锁)
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 16:14
 */
public class TestLazySingleton {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            Object lazySingleton = LazySingleton1.getInstance();
            System.out.println("线程名："+Thread.currentThread().getName()+",线程实例："+lazySingleton);
        };
        Thread t1 = new Thread(runnable,"线程1（非安全）");
        Thread t2 = new Thread(runnable,"线程2（非安全）");
        t1.start();
        t2.start();

        try {
            // 暂停一下，等上一个线程运行完
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runnable runnable2 = ()->{
            Object lazySingleton = LazySingleton2.getInstance();
            System.out.println("线程名："+Thread.currentThread().getName()+",线程实例："+lazySingleton);
        };
        Thread t3 = new Thread(runnable2,"线程3（安全）");
        Thread t4 = new Thread(runnable2,"线程4（安全）");
        t3.start();
        t4.start();

        try {
            Class<?> clazz = Class.forName("util.study.Designpattern.singleton.LazySingleton2");
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            Object instance = declaredConstructor.newInstance();    // 报错。因为前面线程3和4已经创建过实例了。
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/**
 * 一、懒汉模式
 */
class LazySingleton1{
    /**
     * 1.定义一个静态变量。必须是静态的，为了保证作用域是全局的，是属于类级别的，不是对象级别的。
     * 没有赋初值（对比饿汉模式）
     */
    private static LazySingleton1 instance = null;

    /**
     * 2.私有化构造函数。不允许外部方法来实例化
     */
    private LazySingleton1(){
    }
    /**
     * 3.创建一个public 静态方法，共外部调用和创建实例
     */
    public static LazySingleton1 getInstance(){
        if(instance == null){
            instance = new LazySingleton1();
        }
        return instance;
    }
}

/**
 * 二、线程安全的懒汉模式(双重校验锁)
 */
class LazySingleton2 implements Serializable {
    private static final long serialVersionUID = 6778118933495967734L;
    /**
     * 1.定义一个静态变量。必须是静态的，为了保证作用域是全局的，是属于类级别的，不是对象级别的。
     * 没有赋初值（对比饿汉模式）
     *
     * volatile 在这里的作用不是强制刷新数据。而是防止指令重排序。（见下面：静态变量instance 和 对象的堆内存new LazySingleton2() 分配的先后顺序）
     */
    private volatile static LazySingleton2 instance = null;


    /**
     * 2.私有化构造函数。不允许外部方法来实例化
     *      里面防止反射的逻辑我自己写的，效果是：
     *          在调用单例getInstance之前，可以无限使用反射来创建实例。
     *          但是一旦使用过一次getInstance，那么就不允许再使用反射来创建实例了。
     */
    private LazySingleton2() throws Exception {
        if(instance != null){   //如果实例为空，就表示还没有调用过getInstance（但是可能通过反射生成过多个实例）。只要没调用过getInstance()，那么这个口永远留着
            throw new Exception("已经不允许反射破坏单例了");
        }
    }
    /**
     * 3.创建一个public方法，共外部调用和创建实例
     */
    public static LazySingleton2 getInstance()  {
        /**
         * 这一个最外层的if非必须。只是为了提高效率，双重校验的第一重。
         *
         * 这个外层if的作用是为了提高效率。不需要每次都去判断线程锁。
         */
        if(instance == null){
            synchronized (LazySingleton2.class){   // 这里采用静态锁：类名.class。
                // 里面这个if不能省略。因为去掉之后还会产生多线程安全问题（和下面的原因一样。一堆线程过了最外层的if，然后在等锁，等进了锁之后都会创建实例）
                if(instance == null){
                    // 原本的这里是发生多线程安全为题的地方。
                    // （比如执行到这里，时间片到了。另一个线程也是到这里，时间片到了。那么他们下次获得cpu的时候，他俩都会创建实例）
                    try {
                        // 这个异常是因为：在构造方法里面抛出了一个多次调用反射的异常。避免反射破坏单例
                        instance = new LazySingleton2();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 这里 有一个指令重排序的问题。静态变量instance 和 对象的堆内存new LazySingleton2() 分配的先后顺序
                    // 这里 有一个指令重排序的问题。静态变量instance 和 对象的堆内存new LazySingleton2() 分配的先后顺序
                    // instance = new LazySingleton2(); 这一句对象的创建有3个步骤：
                    // 1.给LazySingleton2对象分配内存地址
                    // 2.调用LazySingleton2的构造方法来创建对象，并初始化成员属性为默认是：0或null
                    // 3.instance指向这个LazySingleton2对象的地址。
                    // 如果第二步和第三步顺序调换，即第三步提前，此时instance已经被分配了对象地址了（但此时这个对象还没有初始化，里面的属性是0或null）
                    // 此时线程2进来判断双重校验所最外层的if,发现instance已经不是null了,于是就直接拿去用了，一用就会报错。
                    // 因为instance所指向的对象的属相并没有被初始化。
                }
            }
        }
        return instance;
    }

    /**
     * 重写这个readResolve方法（名字不能错），就可以防止反序列化破坏单例了。
     *      在反序列化的时候，会检查对象有没有这个方法：
     *          如果没有的话，就会用新创建的新实例来返回。
     *          如果有的话，会调用这个方法，因此可以用这个方法返回一个单例的实例。然后这个实例会覆盖之前的新实例，来返回。
     * @return
     */
    private Object readResolve(){
        return instance;
    }
}