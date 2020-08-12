package util.study.Designpattern.singleton;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 【单例模式】：容器式单例。（和枚举一样，都属于注册式单例，属于注册式单例的另一种）
 *      优点：
 *          1.统一管理单例实例。
 *          2.相比于枚举来说，容器式单例属于懒加载，避免了【饿汉式】的缺点。同时容器本身是一个ConcurrentHashMap,是线程安全的
 *      缺点：
 *          实例化过程中存在线程安全问题，类似【懒汉模式】，需要同步来处理。
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/11 21:21
 * @File : TestContainerSingleton
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestContainerSingleton {
    public static void main(String[] args) {
        Object o1 = ContainerSingleton.getInstance("util.study.Designpattern.singleton.Test");
        Object o2 = ContainerSingleton.getInstance("util.study.Designpattern.singleton.Test");
        System.out.println(o1==o2);
    }
}

/**
 * 容器式单例
 */
class ContainerSingleton{
    /**
     * 1.创建一个容器.ConcurrentHashMap是线程安全的
     */
    private static Map<String,Object> ioc = new ConcurrentHashMap<>();
    /**
     * 2.私有化构造函数
     */
    private  ContainerSingleton(){};

    /**
     * 获取实例. name是类的全路径名
     *
     *
     * @return
     */
    public static Object getInstance(String name){
        Object instance = null;
        // 这里类似于【懒汉模式】的校验锁。双重校验锁第一重 思考：这里是不是也应该用双重校验锁效率会更高呢？ 双重校验锁是我自己加的（本来只有第二重）
        if(ioc.containsKey(name)){
            instance = ioc.get(name);
        }else{
            synchronized (ioc){
                // 如果不包含对象实例，就新创建一个。双重校验锁第二重
                if(!ioc.containsKey(name)){
                    try {
                        Constructor<?> declaredConstructor = Class.forName(name).getDeclaredConstructor();
                        declaredConstructor.setAccessible(true);
                        instance = declaredConstructor.newInstance();
                        ioc.put(name,instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    // 如果有的话，就直接取出来，返回出去
                    instance = ioc.get(name);
                }
            }
        }
        return instance;
    }
}
