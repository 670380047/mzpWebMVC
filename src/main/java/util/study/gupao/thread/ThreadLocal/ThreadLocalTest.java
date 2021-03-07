package util.study.gupao.thread.ThreadLocal;

/**
 * ThreadLocal。线程隔离机制。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/2 15:48
 * @File : ThreadLocalTest
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ThreadLocalTest {

    /**
     * 创建一个ThreadLocal变量。并且重写了initialValue()初始化方法来赋值。
     *
     */
    static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
         return  10;
        }
    };

    static int num = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(()->{
                /**
                 * 重点就是这一个get。get放在第一步。 保证num这个值是从ThreadLocal中获取的。就在各个线程之间隔离开了。
                 *      如果是先set再get的话，就起不到线程之间隔离的情况了。因为在set的形参num可能被其他线程已经使用了。
                 *      所以要先get。
                 *
                 * 这个值现在是从ThreadLocal中获取的。
                 *
                 * get()方法里面也有一个初始化方法，会调用initialValue方法来进行初始赋值。
                 */
                num = local.get();
                local.set(num += 5);
//                num += 5;
                System.out.println(Thread.currentThread().getName()+"---"+num);// 结果：每个线程都是15
                local.remove();
            });
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
    }
}
