package util.study.Designpattern.singleton;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 16:47
 * @File : TestStaticInnerSingleton
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.lang.reflect.Constructor;

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

        try {
            // 通过反射获取构造器，来创建实例对象
            Constructor<StaticInnerSingleton> declaredConstructor = StaticInnerSingleton.class.getDeclaredConstructor();
            //暴力访问私有构造器
            declaredConstructor.setAccessible(true);
            //创建实例
            System.out.println(declaredConstructor.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class StaticInnerSingleton{
    /**
     * 1.定义一个静态内部类。 类里面定义了一个外层类的静态实例，并且初始化了实例。
     *      静态内部类：（利用了java的语法规则，实现延迟加载）加载外部类的时候并不会给静态内部类分配空间。只有在用到静态内部类的时候才会分配空间。
     */
    private static class SingletonHolder{
        // 声明了一个外部类类型的静态变量，用来保存单例对象
        private static  StaticInnerSingleton instance;
        //  这个静态快主要是来捕获实例化时可能产生的异常的。  这个异常是反射创建实例的时候会产生的。
        static {
            try {
                instance = new StaticInnerSingleton();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 2.私有化构造方法
     *      抛出异常是为了防止“通过反射强制访问私有构造器”来创建实例，从而破坏了单例。
     */
    private StaticInnerSingleton() throws Exception {
        // 这一步的"判断过程"就会用到静态内部类，直接就加载类，然后实例化了。 所以第一次返回的就是true
        // 因此如果用反射的话，这里一定会返回true，然后抛出异常
        // 这个操作同样适用于【饿汉模式】，因为他们都是类加载过程中实例化单例的，用来“防止反射破坏单例”
        if(SingletonHolder.instance != null ){
            throw new Exception("不允许非法访问 ");
        }
    }

    /**
     * 3.对外提供public 静态方法。
     *      final保证这个方法不会被重写
     * @return
     */
    public static final StaticInnerSingleton getInstance(){
        return SingletonHolder.instance;
    }
}

