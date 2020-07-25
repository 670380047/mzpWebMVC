package util.study.Designpattern.singleton;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 16:14
 * @File : LazySingleton
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 单例模式之懒汉模式：
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
        LazySingleton1 lazySingleton1 = LazySingleton1.getInstance();
        LazySingleton1 lazySingleton11 = LazySingleton1.getInstance();
        System.out.println("懒汉模式（非安全）的两个实例是否相等："+(lazySingleton1 == lazySingleton11));  // true

        LazySingleton2 lazySingleton2 = LazySingleton2.getInstance();
        LazySingleton2 lazySingleton22 = LazySingleton2.getInstance();
        System.out.println("懒汉模式（线程安全）的两个实例是否相等："+(lazySingleton2 == lazySingleton22));
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
        if(instance != null){
            instance = new LazySingleton1();
        }
        return instance;
    }
}

/**
 * 二、线程安全的懒汉模式(双重校验锁)
 */
class LazySingleton2{
    /**
     * 1.定义一个静态变量。必须是静态的，为了保证作用域是全局的，是属于类级别的，不是对象级别的。
     * 没有赋初值（对比饿汉模式）
     */
    private static LazySingleton2 instance = null;

    /**
     * 2.私有化构造函数。不允许外部方法来实例化
     */
    private LazySingleton2(){
    }
    /**
     * 3.创建一个public方法，共外部调用和创建实例
     */
    public static LazySingleton2 getInstance(){
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
                    instance = new LazySingleton2();
                }
            }
        }
        return instance;
    }
}