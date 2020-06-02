package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 10:59
 * @File : PrimeRunTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 10:59
 */
public class PrimeRunTest {
    public static void main(String[] args) {
        /**
         * 7.3 创建该实现类的实例
         */
        PrimeRun primeRun = new PrimeRun();
        /**
         * 7.4 创建Thread类的实例，将上一步中实现类的实例作为参数，传递给Thread的构造器
         */
        Thread t1 = new Thread(primeRun);
        /**
         * 7.5 通过Thread类的实例，来调用start()方法，默认执行run()方法
         */
        t1.start();

        /**
         * 在创建一个该线程实例。  （此时就有两个线程在跑，他们的功能是相同的）
         */
        Thread t2 = new Thread(primeRun);
        t2.start();

        for(int i =100;i<200;i++){
            if(i%2 != 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }


    }
}
