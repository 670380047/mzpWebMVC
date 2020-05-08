package util.lambda.ForkAndJoin;/**
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

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/8 13:38
 */
public class TestForkJoin {

    @Test
    public void test(){
        test1();
        test2();
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
        Long sum = task.invoke();
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

}
