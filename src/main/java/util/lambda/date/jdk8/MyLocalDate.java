package util.lambda.date.jdk8;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/9 14:26
 * @File : MyLocalDate
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * jdk8 的时间处理（多线程使用）
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/9 14:26
 */
public class MyLocalDate {
    public static void main(String[] args) {
        /**
         * 使用jdk设计好的格式 ISO_LOCAL_DATE：yyyy-MM-dd
         */
        // DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

        /**
         * 自定义格式
         */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        /**
         * Runnable 和 Callable的区别是：Runnable没有返回值，Callable有返回值
         */
        Callable<LocalDate> callableTask = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                System.out.println("Callable方式的当前线程名："+Thread.currentThread().getName());
                /**
                 * 这里每次都是产生一个全新的实例，所以是线程安全的
                 */
                return LocalDate.parse("2020-05-09",dtf);   //这里每次都是产生一个全新的实例，所以是线程安全的
            }
        };
        // 用来保存结果
        List<Future<LocalDate>> resultList = new ArrayList<>();

        ExecutorService pools = Executors.newFixedThreadPool(10);

        try {
            for(int i=0;i<10;i++){
                System.out.println("(callablePools)这里是主线程："+Thread.currentThread().getName());
                resultList.add(pools.submit(callableTask));
            }
            resultList.stream().forEach(e-> {
                try {
                    System.out.println(e.get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pools.shutdown();
            if(pools.isShutdown()){
                System.out.println("callablePools线程已关闭");
            }else{
                System.out.println("callablePools线程未关闭，请注意！！！！");
            }
        }
    }
}
