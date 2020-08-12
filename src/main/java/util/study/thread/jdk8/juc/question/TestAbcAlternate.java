package util.study.thread.jdk8.juc.question;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/15 9:27
 * @File : TestAbcAlternate
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 线程按序交替。
 * 线程通信。多个lock的多个Condition互相唤醒。
 * 问题描述：
 *      轮流打印A、B、C。
 *          一共20轮。
 *          每轮都是A先打印5次，然后B打印10次，然后C打印15次。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/15 9:27
 */
public class TestAbcAlternate {


    public static void main(String[] args) {
        Alternate alternate = new Alternate();
        /**
         * 定义lambda表达式写Runnable. 再下面重复使用
         */
        Runnable runnable = ()->{
            for(int i =1;i<=20;i++){
                alternate.loopA(i);
            }
        };

        /**
         * 线程A打印 （这个使用了定义好的lambda表达式：runnable）
         */
        new Thread( runnable,"线程A-1").start();

        /**
         * 线程B打印
         */
        new Thread( ()->{
            for(int i =1;i<=20;i++){
                alternate.loopB(i);
            }
        },"线程B-1").start();

        /**
         * 线程C打印
         */
        new Thread( ()->{
            for(int i =1;i<=20;i++){
                alternate.loopC(i);
            }
        },"线程C-1").start();





        /**
         * 线程A打印
         */
        /**
         * 线程A打印 （这个使用了定义好的lambda表达式：runnable）
         */
        new Thread( runnable,"线程A-2").start();

        /**
         * 线程B打印
         */
        new Thread( ()->{
            for(int i =1;i<=20;i++){
                alternate.loopB(i);
            }
        },"线程B-2").start();

        /**
         * 线程C打印
         */
        new Thread( ()->{
            for(int i =1;i<=20;i++){
                alternate.loopC(i);
            }
        },"线程C-2").start();

    }
}

class Alternate{
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Alternate类的finalize方法执行了");  //好像并没有执行
    }

    Lock lock = new ReentrantLock();
    /**
     * 一个lock可以有多个条件Condition。
     *  然后：各自的等待只能由各自的唤醒来唤醒
     *      condition1.await只能由condition1.signal/condition1.signalAll来唤醒
     *      condition2.await只能由condition2.signal/condition1.signalAll来唤醒
     *      condition2.await只能由condition2.signal/condition1.signalAll来唤醒
     */
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    private int number = 1; // 当前正在执行的线程的标记（默认等于1，表示A线程。2=B线程。 3=C线程）

    /**
     * 循环打印A
     * @param totalLoop   表示第几轮循环
     */
    public void loopA(int totalLoop){
        lock.lock();
        try {
            /**
             * 1.判断是不是当前线程需要执行。
             *      如果不是，就等待。
             */
            while(number != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
            /**
             * 2.打印
             */
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t第"+totalLoop+"轮");
            }
            System.out.println("-------------------------------");
            /**
             * 3.唤醒其他线程
             */
            number = 2;
            condition2.signalAll();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 循环打印B
     * @param totalLoop   表示第几轮循环
     */
    public void loopB(int totalLoop){
        lock.lock();
        try {
            /**
             * 1.判断
             */
            while(number != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
            /**
             * 2.打印
             */
            for(int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t第"+totalLoop+"轮");
            }
            System.out.println("-------------------------------");
            /**
             * 3.唤醒其他线程
             */
            number = 3;
            condition3.signalAll();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 循环打印C
     * @param totalLoop   表示第几轮循环
     */
    public void loopC(int totalLoop){
        lock.lock();
        try {
            /**
             * 1.判断
             */
            while(number != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
            /**
             * 2.打印
             */
            for(int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t第"+totalLoop+"轮");
            }
            System.out.println("-------------------------------");
            /**
             * 3.唤醒其他线程
             */
            number = 1;
            condition1.signalAll();
        } finally {
            lock.unlock();
        }

    }
}
