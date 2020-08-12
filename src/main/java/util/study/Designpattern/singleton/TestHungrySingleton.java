package util.study.Designpattern.singleton;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 16:37
 * @File : HungrySingleton
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.lang.reflect.Constructor;

/**
 * 单例模式之饿汉模式：本身就是线程安全的
 *      优点：本身就是线程安全的，因为是在类加载的时候就初始化了。执行效率高
 *      缺点：如果有大量的单例对象，初始化就加载了的话，会浪费空间。有的单例对象还不经常用到
 *
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
        System.out.println(hungrySingleton1);
        System.out.println(hungrySingleton11);
        System.out.println("饿汉模式的两个实例是否相等："+(hungrySingleton1 == hungrySingleton11));  // true

        // 测试反射能否破坏单例模式
        try {
            Class<?> clazz = HungrySingleton1.class;
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            Object instance = declaredConstructor.newInstance();    // 报错。因为前面已经创建过单例了
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private static HungrySingleton1 instance;

    //  这个静态快主要是来捕获实例化时可能产生的异常的。  这个异常是反射创建实例的时候会产生的。
    static {
        try {
            instance = new HungrySingleton1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.私有化构造函数，避免外部方法创建实例
     */
    private HungrySingleton1() throws Exception {
        // 【饿汉式】这种在静态变量初始化时就实例化的模式，可以保证永远无法返回false。即对象永远不为空，永远无法用反射创建实例。这是静态初始化赋值的优势。
        // 【懒汉式】的懒加载就不行。
        if(HungrySingleton1.instance != null){
            throw new Exception("不允许反射破坏单例");
        }
    }

    /**
     * 3.对外提供一个public 静态方法，来供外部调用实例。
     * @return
     */
    public static HungrySingleton1 getInstance(){
        return instance;
    }
}