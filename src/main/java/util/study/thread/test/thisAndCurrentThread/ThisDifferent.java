package util.study.thread.test.thisAndCurrentThread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/24 20:01
 * @File : ThisDifferent
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *测试this和Thread.currentThread的区别
 * @author maozp3
 * @description:
 * @date: 2019/10/24 20:01
 */
public class ThisDifferent extends Thread{

    public ThisDifferent(){
        System.out.println("ThisDifferent的构造方法-----------start");

        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()="+Thread.currentThread().isAlive());
        System.out.println("Thread.currentThread().getId()="+Thread.currentThread().getId());
        System.out.println("this.getName()="+this.getName());
        System.out.println("this.isAlive()="+this.isAlive());
        System.out.println("this.getId()="+this.getId());
        System.out.println("构造方法中 this == Thread.currentThread() 吗?"+ (this == Thread.currentThread()));
        System.out.println("ThisDifferent的构造方法-----------end");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run--------------start");

        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()="+Thread.currentThread().isAlive());
        System.out.println("Thread.currentThread().getId()="+Thread.currentThread().getId());
        System.out.println("this.getName()="+this.getName());
        System.out.println("this.isAlive()="+this.isAlive());
        System.out.println("this.getId()="+this.getId());
        System.out.println("run方法中 this == Thread.currentThread() 吗?"+ (this == Thread.currentThread()));
        System.out.println("run--------------end");

    }
}
