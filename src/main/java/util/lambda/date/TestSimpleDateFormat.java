package util.lambda.date;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/9 11:03
 * @File : TestSimpleDateFormat
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池的使用
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/9 11:03
 */
public class TestSimpleDateFormat {

    public static void main(String[] args) {
//        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");   // 线程不安全
        /**
         * Runnable 和 Callable的区别是：Runnable没有返回值，Callable有返回值
         */
        Callable<Date> callableTask = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
//                return sdf.parse("2020-05-09");       // 线程不安全
                System.out.println("Callable方式的当前线程名："+Thread.currentThread().getName());
                return DateFormatThreadLocal.dateConvert("2020-05-09");
            }
        };
        // 用来保存结果
        List<Future<Date>> resultList = new ArrayList<>();
        /**
         * 创建线程池
         */
        ExecutorService callablePools = Executors.newFixedThreadPool(10); // 创建拥有10个线程的线程池
        try {
            for(int i = 0; i < 10; i++){
                System.out.println("(callablePools)这里是主线程："+Thread.currentThread().getName());
                resultList.add(callablePools.submit(callableTask));
            }
            for(Future<Date> future : resultList){
                System.out.println(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            callablePools.shutdown();   //记得用完要关闭线程池
            if(callablePools.isShutdown()){
                System.out.println("callablePools线程已关闭");
            }else{
                System.out.println("callablePools线程未关闭，请注意！！！！");
            }
        }


        System.out.println("=======================Runnable========================");
        /**
         * Runnable 和 Callable的区别是：Runnable没有返回值，Callable有返回值
         */
        Runnable runnableTask1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable当前线程名："+Thread.currentThread().getName());
                System.out.println("Runnable 和 Callable的区别是：Runnable没有返回值");
            }
        };
        /**
         * 创建线程池
         */
        ExecutorService runnablePools = Executors.newFixedThreadPool(10); // 创建拥有10个线程的线程池
        try {
            for(int i = 0; i < 10; i++){
                System.out.println("(runnablePools)这里是主线程："+Thread.currentThread().getName());
                runnablePools.submit(runnableTask1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            runnablePools.shutdown();       //记得用完要关闭线程池
            if(runnablePools.isShutdown()){
                System.out.println("runnablePools线程已关闭");
            }else{
                System.out.println("runnablePools线程未关闭，请注意！！！！");
            }
        }

    }
}
