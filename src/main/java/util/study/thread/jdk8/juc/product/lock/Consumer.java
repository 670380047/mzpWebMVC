package util.study.thread.jdk8.juc.product.lock;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/14 13:15
 * @File : Consumer
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * lock.lock() 显式锁处理生产者消费者问题（线程通信）
 * 线程通信。消费者
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/14 13:12
 */
public class Consumer implements Runnable{
    private ClerkLock clerk;

    public Consumer(ClerkLock clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            clerk.sale();
        }
    }
}
