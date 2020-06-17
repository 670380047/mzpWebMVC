package util.study.thread.jdk8.juc.pool;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/15 17:13
 * @File : TestScheduledThreadPool
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.Random;
import java.util.concurrent.*;

/**
 * 一、线程池。提供了一个线程队列，队列中保存着所有等待状态的线程。避免了“创建与销毁”线程的额外开销。
 * 二、线程池的体系结构：
 *      1.java.util.concurrent.Executor接口: 负责线程的使用与调度的根接口
 *          1.1 ExecutorService接口: 是Executor的子接口，是线程池的主要接口
 *              1.1.1 ThreadPoolExecutor类：是ExecutorService接口的实现类
 *              1.1.2 ScheduledExecutorService接口：是ExecutorService的子接口。负责线程的调度。
 *                  1.1.2.1 ScheduledThreadPoolExecutor类：它继承了ThreadPoolExecutor、同时实现了ScheduledExecutorService接口。
 * 三、工具类：Executors
 *      1.ExecutorService  newFixedThreadPool(int size):创建固定大小(size)的连接池   ---》TestThreadPool类中
 *      2.ExecutorService  newCachedThreadPool():缓存线程池，线程池中的线程数量不固定，可以根据需要自动的更改数量。
 *      3.ExecutorService  newSingleThreadPool():现场池中只要一个线程。等效newFixedThreadPool(1)
 *      4.ExecutorService  newScheduledThreadPool():创建固定大小的线程池，可以延迟或定时执行任务  ---》TestScheduledThreadPool类中
 *
 * 四、用Runnable创建线程池实例
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/15 14:28
 */
public class TestScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        /**
         * 创建一个延迟2秒钟执行的线程。
         * pool.schedule(线程的实例，延迟的时间，时间的单位)
         */
        for(int i=0;i<6;i++){
            Future<Integer> result = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    //生成0--100的随机数
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    return num;
                }
            }, 2, TimeUnit.SECONDS);

            try {
                /**
                 * 获取运算结果
                 */
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        /**
         * 关闭线程池
         */
        pool.shutdown();

    }
}
