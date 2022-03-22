package util.study.thread.jdk8.juc.pool;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/15 14:28
 * @File : TestThreadPool
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.*;
import java.util.concurrent.*;

/**
 *  （mzp：本类中包含有批量处理数据的demo，在 TestCallablePool 方法中）
 *
 * 一、线程池。提供了一个线程队列，队列中保存着所有等待状态的线程。避免了“创建与销毁”线程的额外开销。
 * 二、线程池的体系结构：
 *      1.java.util.concurrent.Executor接口: 负责线程的使用与调度的根接口
 *          1.1 ExecutorService接口: 是Executor的子接口，是线程池的主要接口
 *              1.1.1 ThreadPoolExecutor类：是ExecutorService接口的实现类
 *              1.1.2 ScheduledExecutorService接口：是ExecutorService的子接口。负责线程的调度。
 *                  1.1.2.1 ScheduledThreadPoolExecutor类：它继承了ThreadPoolExecutor、同时实现了ScheduledExecutorService接口。
 * 三、工具类：Executors
 *      1.ExecutorService  newFixedThreadPool(int size):创建固定大小(size)的连接池  ---》TestThreadPool类中
 *      2.ExecutorService  newCachedThreadPool():缓存线程池，线程池中的线程数量不固定，可以根据需要自动的更改数量。
 *      3.ExecutorService  newSingleThreadPool():现场池中只要一个线程。等效newFixedThreadPool(1)
 *      4.ExecutorService  newScheduledThreadPool():创建固定大小的线程池，可以延迟或定时执行任务  ---》TestScheduledThreadPool类中
 *
 * 四、用Runnable创建线程池实例
 * 提交任务有两种方式：
 *      1. submit()
 *      2. execute()
 *      两种方式的区别：
 *          1. 需要的参数不同：
 *              submit() 参数 (Runnable) 或者 (Runnable 和 结果 T) 或者 (Callable)。
 *              而execute() 参数只能是 Runnable 。
 *          2.  返回值不同：
 *              submit() 有返回值。
 *              而execute() 没有返回值。
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/15 14:28
 */
public class TestThreadPool {
    public static void main(String[] args) {
        /**
         * Runnable方式
         */
//        TestRunnablePool();
        /**
         * Callable方式
         */
        TestCallablePool();
    }

    /**
     * 四、用Runnable创建线程池实例
     */
    public static void TestRunnablePool(){
        /**
         * 1.创建指定个数的线程池
         *      创建了5个线程的线程池
         */
        ExecutorService pool = Executors.newFixedThreadPool(5);
        /**
         * 2.创建一个Runnable接口实现类的实例
         */
        RunnablePool threadPool = new RunnablePool();
        /**
         * 3.为线程池中的线程分配任务
         *   submit() 有返回值。而execute() 没有返回值；
         */
        for(int i=0;i<10;i++){
            pool.submit(threadPool);
//            pool.execute(threadPool);
        }
        /**
         * 4.关闭线程池
         *  pool.shutdown():相对平稳的关闭线程池。不会再接收新的资源了，如果现有任务还没结束，就会等任务结束之后再关闭。
         *  pool.shutdownNow()：立刻关闭线程。如果任务没结束，也会强制关闭结束任务
         */
        pool.shutdown();
//        pool.shutdownNow();
    }

    /**
     * 模拟一个大数据。保存在list中。  随后用线程池对这个list进行处理
     */
    private static LinkedList linkedList = new LinkedList();

    public static void TestCallablePool(){
        //模拟创建一个大容量的数据。
        createList();

        /**
         * 1.创建指定个数的线程池。5个
         */
        ExecutorService pool = Executors.newFixedThreadPool(5);


        /**
         * 3.执行Callable需要FutureTask实现类的支持，用于接收运算结果。
         *  FutureTask是Future和Runnable接口的实现类
         */
        List<FutureTask<Map<String,List>>> callResult = new ArrayList<>();

        //线程需要处理的任务的数量。  （比如：这里是5个线程，处理10个任务）
        int threadCount = 10;
        Long start = System.currentTimeMillis();
        for(int i=0;i<threadCount;i++){
            int size = linkedList.size();
            /**
             * 将数据分成不同的子集，作用是让不同的线程处理不同的子集。
             */
            List subList = linkedList.subList(size / threadCount * i, size / threadCount * (i + 1));

            /**
             * 2.创建Callable接口实现类的实例
             * （这里传入了需要被处理的数据subList）
             */
            CallablePool callablePool = new CallablePool(subList);

            /**
             * 开5个线程去跑10次任务，会得到10个结果。
             * 因为只有5个线程，所以有5个线程会被重复使用（有的不会被重复使用，而有的会被使用多次）
             */
            callResult.add( (FutureTask<Map<String,List>>) pool.submit(callablePool) );
        }
        /**
         * 4.关闭线程池
         *  pool.shutdown():相对平稳的关闭线程池。不会再接收新的资源了，如果现有任务还没结束，就会等任务结束之后再关闭。
         *  pool.shutdownNow()：立刻关闭线程。如果任务没结束，也会强制关闭结束任务
         */
        pool.shutdown();
//        pool.shutdownNow();


        try {
            System.out.println("遍历结果");
            for(FutureTask futureTask : callResult){
                System.out.println(futureTask.get());
            }
            Long end = System.currentTimeMillis();
            Long result = end - start;
            System.out.println("处理过程耗时："+result+"毫秒");  //5个线程，打印10W条数据，耗时49毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟创建一个大数据量的list，然后用线程池来完成这些数据的处理
     */
    public static void createList(){
        Long start = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            linkedList.add(i);
        }
        Long end = System.currentTimeMillis();
        Long result = end - start;
        System.out.println("初始化数据耗时："+result+"毫秒");
    }

}

/**
 * Runnable接口的实现类
 */
class RunnablePool implements Runnable{


    @Override
    public void run() {
            System.out.println("Runnable接口创建线程池："+Thread.currentThread().getName());
    }
}




/**
 * Callable接口的实现类
 */
class CallablePool implements Callable<Map<String,List>> {
    private int i = 0;

    //模拟大容量的数据
    List list ;

    public CallablePool(List linkedList) {
        this.list = linkedList;
    }

    @Override
    public Map<String,List> call() throws Exception {
        Map<String,List> map = new HashMap<>();
        System.out.println("Callable接口创建线程池："+Thread.currentThread().getName()+":"+list);

        for(Object num : list){
            System.out.print(num+",");
        }
        System.out.println();
        map.put(Thread.currentThread().getName(),list);
        return map;
    }
}

