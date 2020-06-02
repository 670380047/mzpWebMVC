package util.study.thread.study.thread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 10:29
 * @File : PrimeThread
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 10:29
 */

/**
 * 6.1 声明一个类继承Thread类
 */
public class PrimeThread extends Thread{
    /**
     * 6.2 重写run方法，同时编写线程执行体，也就是run方法的方法体
     * run()方法是：线程执行体
     */
    public void run(){
        for(int i = 0;i<100;i++){
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
