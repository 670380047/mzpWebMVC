package util.study.thread.jdk8.juc.product;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/14 13:18
 * @File : TestProducer
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 处理生产者消费者问题（线程通信）中的“虚假唤醒”
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/14 13:18
 */
public class TestProducer {
    public static void main(String[] args) {
        /**
         * 店员clerk是共享数据
         */
        Clerk clerk = new Clerk();
        Producer producerA = new Producer(clerk);
        Consumer customerA = new Consumer(clerk);

        Producer producerB = new Producer(clerk);
        Consumer customerB = new Consumer(clerk);

        new Thread(producerA,"生产者A").start();
        new Thread(customerA,"消费者A").start();
        new Thread(producerB,"生产者B").start();
        new Thread(customerB,"消费者B").start();
    }
}
