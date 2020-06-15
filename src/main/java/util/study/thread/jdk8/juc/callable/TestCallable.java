package util.study.thread.jdk8.juc.callable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 18:36
 * @File : TestCallable
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程创建方式三：实现Callable接口,
 *       1.对比实现Runnable接口
 *           1.1. Callable接口支持泛型
 *           1.2. Callable接口中 call方法有返回值，并且可以抛出异常。
 *       2.执行Callable需要FutureTask实现类的支持，用于接收运算结果。
 *          FutureTask是Future和Runnable接口的实现类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 18:38
 */
public class TestCallable {
    public static void main(String[] args) {
        /**
         * 1.实例化一个Callable接口实现类的实例
         */
        MyCallable myCallable = new MyCallable();
        /**
         * 2.执行Callable需要FutureTask实现类的支持，用于接收运算结果。
         *  FutureTask是Future和Runnable接口的实现类
         */
        FutureTask<Integer> result = new FutureTask<>(myCallable);
        /**
         * 3.同Runnable接口一样，需要放入Thread类中。
         *  这里入参依旧是Runnable类型。刚好FutureTask就是Runnable的实现类（孙子类）
         */
        Thread t1 = new Thread(result);
        t1.start();
        /**
         *  4.通过FutureTask实例的get方法，就可以获取到执行结果了
         *      有两个异常需要处理.
         *    注意！！
         *      当线程没有执行完的时候，下面的result.get()不会执行，会一直在等待。类似于闭锁
         *      因此FutureTask也可用于闭锁
         */
        try {
           Integer sum = result.get();
            System.out.println(sum);
            System.out.println("------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 实现Callable接口来创建线程
 */
class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=0;i<=100;i++){
            sum += i;
            System.out.println(i);
        }
        return sum;
    }
}


