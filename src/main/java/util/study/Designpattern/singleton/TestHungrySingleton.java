package util.study.Designpattern.singleton;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 16:37
 * @File : HungrySingleton
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 单例模式之饿汉模式：本身就是线程安全的
 *      步骤：1. 创建一个私有的静态变量，并且初始化一个该类的实例。
 *             对比懒汉（这里初始化了）。
 *             静态变量初始化时就创建了实例，就避免了多线程问题可能引发的多个实例，因为线程都还没开始呢。
 *           2.私有化构造函数，避免外部方法创建实例
 *           3.对外提供一个public 静态方法，来供外部调用实例。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 16:37
 */
public class TestHungrySingleton {
    public static void main(String[] args) {
        HungrySingleton1 hungrySingleton1 = HungrySingleton1.getInstance();
        HungrySingleton1 hungrySingleton11 = HungrySingleton1.getInstance();
        System.out.println("饿汉模式的两个实例是否相等："+(hungrySingleton1 == hungrySingleton11));  // true

    }
}

/**
 * 饿汉模式。本身就是线程安全的
 */
class HungrySingleton1{
    /**
     * 1. 创建一个私有的静态变量，并且初始化一个该类的实例。
     *      对比懒汉（这里初始化了）。
     *      静态变量初始化时就创建了实例，就避免了多线程问题可能引发的多个实例，因为线程都还没开始呢。
     */
    private static HungrySingleton1 instance = new HungrySingleton1();

    /**
     * 2.私有化构造函数，避免外部方法创建实例
     */
    private HungrySingleton1(){

    }

    /**
     * 3.对外提供一个public 静态方法，来供外部调用实例。
     * @return
     */
    public static HungrySingleton1 getInstance(){
        return instance;
    }
}