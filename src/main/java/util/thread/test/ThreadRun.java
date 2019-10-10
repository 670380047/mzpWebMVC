package util.thread.test;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/9/27 19:35
 * @File : ThreadRun
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/9/27 19:35
 */
public class ThreadRun {
    public static void main(String[] args) {
//        NoShareVar noShareVar1 = new NoShareVar("ellia");
//        NoShareVar noShareVar2 = new NoShareVar("不点儿");
//        NoShareVar noShareVar3 = new NoShareVar("mzp");
//
//        noShareVar1.start();
//        noShareVar2.start();
//        noShareVar3.start();

        //因为Thread类也实现了Runnable接口。所有构造函数Thread(Runnable targer)不止可以传Runnable接口的对象，
        //还可以传一个Thread类的对象。这样就可以将一个Thread中的run()方法交给其他线程调用了。
        NoShareVar noShareVar = new NoShareVar("ellia");  //创建一个NoShareVar线程的对象，名字叫ellia。
        System.out.println(noShareVar.getName()+"线程begin========isAlive："+noShareVar.isAlive());
        Thread a = new Thread(noShareVar,"a");   //分别把NoShareVar线程的对象分配给其他线程
        Thread b = new Thread(noShareVar,"b");   //这样其他线程都可以调用NoShareVar线程中的main方法
        Thread c = new Thread(noShareVar,"c");
        Thread d = new Thread(noShareVar,"d");
        Thread e = new Thread(noShareVar,"e");
        System.out.println(a.getName()+"线程begin========isAlive："+a.isAlive());

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        System.out.println(a.getName()+"线程begin========isAlive："+a.isAlive());
    }
}
