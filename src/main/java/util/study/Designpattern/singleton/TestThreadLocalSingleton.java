package util.study.Designpattern.singleton;

/**
 * 【单例模式】：ThreadLocal单例
 *      ThreadLocal单例是保证在一个线程内部，对象是单例的。但在多个线程之间并不要求。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 0:48
 * @File : TestThreadLocalSingleton
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestThreadLocalSingleton {
    public static void main(String[] args) {
        System.out.println("=============同一个线程内得到的对象都是单例================");
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());

        System.out.println("=============不同线程间得到的对象就不相同了================");
        Runnable runnable = ()->{
            Object instance = ThreadLocalSingleton.getInstance();
            System.out.println("线程名："+Thread.currentThread().getName()+",线程实例："+instance);
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}

class ThreadLocalSingleton{
    /**
     * 构建一个ThreadLocal变量，存的类型就是本类ThreadLocalSingleton，即想要保存的单例类型。这里重写了初始化方法initialValue()，初始化赋值了单例对象。
     *      这也是静态变量赋值，【饿汉式】，类加载时初始化。  但是，根本不用担心，因为就在在自己线程内部使用，根本不存在多线程问题。
     */
    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = new ThreadLocal<ThreadLocalSingleton>(){
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    /**
     * 私有化构造方法
     */
    private ThreadLocalSingleton(){};

    /**
     * 提供一个全局的访问点
     * @return
     */
    public static ThreadLocalSingleton getInstance(){
        // get()方法得到的结果就是ThreadLocal里面存储的东西（这里就是单例对象）。
        // 每个线程都有一个ThreadLocal。get的时候先通过线程获取到一个map，然后map的key就是当前对象。（个人理解）
        return threadLocalInstance.get();
    }
}
