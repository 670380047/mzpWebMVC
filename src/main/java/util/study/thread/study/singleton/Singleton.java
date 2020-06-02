package util.study.thread.study.singleton;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 10:44
 * @File : Singleton
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 单例模式之：懒汉模式（线程安全）
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 10:44
 */
public class Singleton {

    /**
     * 定义一个静态的类类型变量，用来做为唯一的实例。 初始为null
     */
    private static Singleton instance = null ;

    /**
     * 把构造器设置为私有。不允许外部实例化
     */
    private Singleton(){}

    /**
     * 定义一个public方法。供外部方法调用，来获取实例
     */
    public static Singleton getInstance(){
        // 这个外层if的作用是为了提高效率。不需要每次都去判断线程锁。
        if(instance == null){
            synchronized (Singleton.class){
                // 里面这个if不能省略。因为去掉之后还会产生多线程安全问题（和下面的原因一样。一堆线程过了最外层的if，然后在等锁，等进了锁之后都会创建实例）
                if(instance == null){
                    // 原本的这里是发生多线程安全为题的地方。
                    // （比如执行到这里，时间片到了。另一个线程也是到这里，时间片到了。那么他们下次胡哦哦的cpu的时候，他俩都会创建实例）
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
