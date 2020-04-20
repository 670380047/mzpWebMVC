package util.study.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/11 14:33
 * @File : AtomicDemo
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、原子性问题
 *        k = i++ 这一步在底层的三个步骤是：读-改-写
 *            1.  int temp = i
 *            2.  i = i + 1
 *            3.  k = tem         //最终实际是把temp的值给k了， 并不是把i++的值给k了
 * 二、 原子变量：jdk1.5之后，java.util.concurrent.atomic 包下提供了常用的原子变量.
 *          原子变量特性：
 *              1.变量都用 volatile保证内存可见性
 *              2.CAS（Compare And Swap）算法保证数据的原子性
 *                  2.1 CAS算法是硬件对于并发操作共享数据的支持
 *                  2.2 CAS包含三个操作数：
 *                      内存值：V   （线程从主存中读出来的值）
 *                      预估值：A    (准备修改的时候，此时主存中的值。这个值可能是其他线程修改后的值，会造成V和A不相等。然后当前线程就会更新失败，什么也不做)
 *                      更新值：B    (当前线程的计算结果，准备更新会主存中去)
 *                      动作：当且仅当 V == A时， 就让V = B。 否则什么都不做
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/11 14:33
 */
public class AtomicDemo {

    /**
     *  i++ 的原子性问题. 在底层的运行步骤
     */
    public static void test1(){
        int i = 10;
        /**
         * k = i++ 这一步在底层的三个步骤是：读-改-写
         *  1.  int temp = i
         *  2.  i = i + 1
         *  3.  k = tem         //最终实际是把temp的值给k了， 并不是把i++的值给k了
         */
        int k = i++;
        System.out.println(k);  // 结果还是10


        /**
         * n = n++ 这一步在底层的三个步骤是：读-改-写
         *  1.  int temp = i
         *  2.  n = n + 1
         *  3.  n = tem         //最终实际是把temp的值给n了， 并不是把i++的值给n了
         */
        int n = 10;
        n = n++;
        System.out.println(n);  // 结果还是10

    }

    /**
     * 原子变量
     */
    public static void main(String[] args) {
//        test1();
        AtomicDemoRunnable runnable = new AtomicDemoRunnable();

//        for(int i=0;i<10;i++){
//            new Thread(runnable).start();
//        }

        /**
         * 使用线程池
         */
        ExecutorService pools = Executors.newFixedThreadPool(10);  //创建10个线程的线程池
        for(int i=0;i<10;i++){  //使用10个线程
            pools.submit(runnable);
        }
        pools.shutdown();
    }


}


class AtomicDemoRunnable implements  Runnable{
//    private int serialNum = 0;
    /**
     * 定义原子变量
     */
    private AtomicInteger serialNum = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+":"+getSerialNumInt());

    }

    public int getSerialNumInt() {
        /**
         * 原子变量获取值：getAndIncrement()  获取并递增
         */
        return serialNum.getAndIncrement();

//        return serialNum.getAndAdd(2);       // 先获取再自增2
//        return serialNum.addAndGet(2);       // 先自增2再获取
    }


}