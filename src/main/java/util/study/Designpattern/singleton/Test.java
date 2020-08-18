package util.study.Designpattern.singleton;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/10 21:31
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            Object lazySingleton = ContainerSingleton.getInstance("util.study.Designpattern.singleton.Test");
            System.out.println("线程名："+Thread.currentThread().getName()+",线程实例："+lazySingleton);
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();

    }
}
