package util.study.lambda.ForkAndJoin;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/8 13:38
 * @File : TestForkJoin
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * java8 并行流.  (并行流底层就是fork/join模式)
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/8 13:38
 */
public class TestForkJoin {

    @Test
    public void test(){
        test1();
        test2();
        test3();
    }

    /**
     * 使用forkJoin框架
     */
    @Test
    public void test1(){
        Instant startTime = Instant.now();  // jdk8的时间

        // 需要一个forkJoin池
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new MyForkJoinCalculate(0,5000000000L);
        long sum = pool.invoke(task);   // 开始执行任务
//        Long sum = task.invoke();   // 开始执行任务
        System.out.println(sum);

        Instant endTime = Instant.now();
        System.out.println("forkJoin耗费时间为："+ Duration.between(startTime,endTime).toMillis()+"毫秒");
    }

    /**
     * 普通for循环
     */
    @Test
    public void test2(){
        Instant startTime = Instant.now();  // jdk8的时间

        long sum = 0L;
        for(long i =0; i<=5000000000L;i++){
            sum += i;
        }
        System.out.println(sum);

        Instant endTime = Instant.now();
        System.out.println("普通for循环耗费时间为："+ Duration.between(startTime,endTime).toMillis()+"毫秒");
    }

    /**
     * java8 并行流求和.  (并行流底层就是fork/join模式)
     */
    @Test
    public void test3(){
        Instant startTime = Instant.now();  // jdk8的时间

        long sum = 0L;
        sum = LongStream.rangeClosed(0,5000000000L)// 数据范围
                .parallel() // 开启并行流
                .reduce(0,Long::sum);   //求和
        System.out.println(sum);

        Instant endTime = Instant.now();
        System.out.println("java8并行流耗费时间为："+ Duration.between(startTime,endTime).toMillis()+"毫秒");
    }

}
