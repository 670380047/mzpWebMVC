package util.lambda.ForkAndJoin;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/8 13:19
 * @File : MyForkJoinCalucate
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.RecursiveTask;

/**
 * fork/join模式。 线程拆分合并模式（样例）。（采用的是任务窃取模式：当一个线程队列空闲的时候，他会去“偷”另一个线程的任务来执行。
 *      从而避免一个线程A因为前期阻塞了一会，耽误了时间，而此时其他线程B、C...跑完了，已经处于空闲位置状态，而线程A还是十分忙碌。  窃取模式就会让B和C取“偷”A的任务过来做）
 *  需要继承 RecursiveAction 或 RecursiveTask。 前者的compute方法没有返回值，后这有
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/8 13:19
 */
public class MyForkJoinCalculate extends RecursiveTask<Long> {
    private static final long serialVersionUID = -8643450737133548548L;

    private long start;
    private long end;

    // 拆分到不能拆分的临界值。到临界值就不再拆了
    private  static final long THRESHOLD = 10000;

    public MyForkJoinCalculate() {
    }
    public MyForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     *  例子的功能：用来求总和
     * @return
     */
    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THRESHOLD){        // 拆分到不能再拆分时，开始执行任务
            long sum = 0;
            for(long i = start; i<=end; i++ ){
                sum += i;
            }
            return sum;
        }else{  // 继续拆分
            long middle = (start + end)/2;

            MyForkJoinCalculate left = new MyForkJoinCalculate(start,middle);
            left.fork(); // 拆分子任务，同时压入线程队列

            MyForkJoinCalculate right = new MyForkJoinCalculate(middle+1,end);
            right.fork(); // 拆分子任务，同时压入线程队列

            return left.join() + right.join();  // 合并总和
        }
    }
}
