package util.study.Designpattern.singleton;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 16:47
 * @File : TestStaticInnerSingleton
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 单例模式之静态内部类：本身就是线程安全的
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 16:47
 */
public class TestStaticInnerSingleton {
    public static void main(String[] args) {
        StaticInnerSingleton staticInnerSingleton1 = StaticInnerSingleton.getInstance();
        StaticInnerSingleton staticInnerSingleton2 = StaticInnerSingleton.getInstance();
        System.out.println("静态内部类模式的两个实例是否相等："+(staticInnerSingleton1 == staticInnerSingleton2));  // true

    }
}

class StaticInnerSingleton{
    /**
     * 1.定义一个静态内部类。 类里面定义了一个外层类的静态实例，并且初始化了实例。
     */
    private static class SingletonHolder{
        private static  StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    /**
     * 2.私有化构造方法
     */
    private StaticInnerSingleton(){
    }

    /**
     * 3.对外提供public 静态方法。
     * @return
     */
    public static StaticInnerSingleton getInstance(){
        return SingletonHolder.instance;
    }
}

