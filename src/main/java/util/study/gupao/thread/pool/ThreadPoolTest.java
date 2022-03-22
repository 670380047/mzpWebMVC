package util.study.gupao.thread.pool;

import java.util.Date;
import java.util.concurrent.*;

/**
 *  线程池的使用demo。
 *  自定义线程池，继承ThreadPoolExecutor类。然后重写构造方法，自定义参数
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/12 12:56
 * @File : ThreadPoolDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ThreadPoolTest extends ThreadPoolExecutor {

    /**
     * 创建一个缓存时间的CMH
     */
    private ConcurrentHashMap<String, Date> startTime ;

    public ThreadPoolTest() {
        super(5, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        startTime = new ConcurrentHashMap<>();

    }

    public ThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTime = new ConcurrentHashMap<>();
    }


    /**
     * 继承了ThreadPoolExecutor之后。
     * 在当前类中重写terminated()，然后用当前类实例化对象的时候，就会优先使用当前类的这个方法。
     */
    @Override
    protected void terminated() {
        super.terminated();
        System.out.println("线程池结束了。重写的terminated()方法");
    }

    /**
     * 继承了ThreadPoolExecutor之后。
     * 在当前类中重写beforeExecute()，然后用当前类实例化对象的时候，就会优先使用当前类的这个方法。
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        startTime.put(String.valueOf(r.hashCode()),new Date());
    }

    /**
     * 继承了ThreadPoolExecutor之后。
     * 在当前类中重写afterExecute()，然后用当前类实例化对象的时候，就会优先使用当前类的这个方法。
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        Date startDate = this.startTime.remove(String.valueOf(r.hashCode()));
        Date endDate = new Date();
        long resultDate = endDate.getTime()-startDate.getTime();

        System.out.println("任务耗时："+resultDate);
        System.out.println("核心线程数："+this.getCorePoolSize());
        System.out.println("最大线程数："+this.getMaximumPoolSize());
        System.out.println("当前线程数："+this.getPoolSize());
        System.out.println("线程空闲时间："+this.getKeepAliveTime(TimeUnit.MILLISECONDS));
        System.out.println("任务总数:"+this.getTaskCount());
        System.out.println("已经完成的任务总数："+this.getCompletedTaskCount());
        System.out.println("正在执行的任务线程数："+this.getActiveCount());

    }

    /**
     * 不使用Executors工具类。 而是直接使用ThreadPoolExecutor类来创建线程池。
     *      优点：可以自定义参数，来优化线程池
     *      缺点：需要了解参数的意义。对新手不友好
     */
    private void customPool(){
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());

        ThreadPoolTest threadPoolTest = new ThreadPoolTest();

        System.out.println("这是我自定义参数，来创建线程池。不使用Executors工具类");

        for (int i = 0; i < 5; i++) {
            /**
             * execute()提交的任务没有返回值
             */
            threadPoolTest.execute(new WorkerDemo());
        }
        threadPoolTest.shutdown();

    }


    /**
     * 使用Executors工具类调用工厂方法，来创建线程池。
     *      优点：简单、方便。适合新手，不用了解参数的作用
     *      缺点：对于细节把控不严谨。效率不是最好的（因为可以通过参数来优化）。
     */
    private void threadPoolTest(){
        //创建固定数量线程的线程池。这里指定线程池核心线程数和最大线程数都是3
        ExecutorService executors1 = Executors.newFixedThreadPool(3);
        //创建只有一个核心线程的线程池
        ExecutorService executors2 = Executors.newSingleThreadExecutor();
        //创建具有缓存功能的线程池。核心线程数为0，最大线程数是2^31-1。，当线程够用的时候会复用，当线程不够用的时候会创建新的。
        //      默认如果60秒没有使用的线程回被清除掉。
        ExecutorService executors3 = Executors.newCachedThreadPool();
        //创建具有定时任务功能的线程池
        ExecutorService executors4 = Executors.newScheduledThreadPool(3);

        //给一个线程池提交了5个任务。
        for (int i = 0; i < 5; i++) {
            /**
             * execute()提交的任务没有返回值
             */
            executors1.execute(new WorkerDemo());
        }



        /**
         * submit()提交的任务是带有返回值的。一般用来提交callable接口的实现类的
         * 然后用Future对象来接收返回的结果
         */
        Future<?> submit = executors1.submit(new WorkerDemo());
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * 关闭线程池。正常的关闭，如果有任务没有执行完，就先执行完，再关闭
         */
        executors1.shutdown();
    }






    public static void main(String[] args) {
        ThreadPoolTest threadPoolTest = new ThreadPoolTest();

//        threadPoolDemo.threadPoolTest();
        threadPoolTest.customPool();

//        Thread t = new Thread(new WorkerDemo());
//        /**
//         * Alive状态就是 “可运行、等待、计时等待”状态。即 start之后，还没有死亡。这期间的状态。
//         */
//        System.out.println(t.isAlive());
//        t.start();
//        System.out.println(t.isAlive());

//        System.out.println(2&2); //与运算，用二进制来计算，返回的结果是十进制

        int a = -3;
        System.out.println(a);  // -3
        //-3的原码： 10000000000000000000000000000011  ---》反码： 11111111111111111111111111111100 ---》 补码：11111111111111111111111111111101
        System.out.println(Integer.toBinaryString(a)); //负数的二进制是用补码表示的。
        System.out.println((a>>>1)); // 2147483646。  即 11111111111111111111111111111101 ---》 01111111111111111111111111111110
        System.out.println(a);      // -3
        int b = (1<<31)-1 ;     // 2147483647
        System.out.println(b);  // 2147483647

        System.out.println(a+2);
        System.out.println(Integer.toBinaryString(a+2));

    }
}


