package util.study.gupao.thread.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *  阻塞队列的使用。（其实就是Condition的一种实现场景。生产者消费者模型）。个人感觉，消息队列类似这种机制。 消息队列用来起到缓冲的作用
 *         如果队列满了，写入操作就会被阻塞
 *         如果队列为空，读出操作就会被阻塞
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/10 15:31
 * @File : BlockedQueueDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class BlockingQueueDemo {
    /**
     * 这是一个共享变量。是一个指定大小的数组类型的队列（阻塞队列）。运用的就是Condition条件。 多个线程去操作的。
     *
     * 他就是一个“生产者/消费者”场景的实现案例。
     *
     * 除了同步队列之后，还有两个条件队列，分别是 notFull 和 notEmpty 这个两个条件队列
     */
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

    public void blockedQueueTest(){
        try {
            /**
             * 添加元素1
             * offer第一种：添加成功就返回true，并且唤醒“消费者”。添加失败就返回false
             *
             * offer第二种：带有超时时间的添加。
             */
            blockingQueue.offer("offer");
            blockingQueue.offer("超时offer",10, TimeUnit.MILLISECONDS);

            /**
             * 添加元素2
             *
             * 这是添加方法不会阻塞。内部调用的offer(e)。根据offer(e)返回的true或者false，来判断是否插入成功。
             *
             * 返回false说明队列已经满了。
             * 如果队列中已经满了的话，会抛出异常“IllegalStateException("Queue full")”
             */
            blockingQueue.add("小毛");
            /**
             * 添加元素3
             *
             * put()方法是个阻塞方法：如果队列已经满了的话，线程会阻塞。下次插入线程就会阻塞（即阻塞生产者）
             *
             * 然后每一次成功插入，都会notEmpty.signal()，发一个信号。将notEmpty条件队列中的一个线程迁移到同步队列中去。
             *      然后内部unLock()的时候，就会去唤醒一个同步队列中的线程
             */
            blockingQueue.put("小明");




            /**
             * 取元素并移除元素 1
             * take()方法也是一个阻塞方法：如果队列中的元素为空的话，线程就会阻塞。
             *
             * 然后每一次成功插入，都会notFull.signal()，发一个信号。将notFull条件队列中的一个线程迁移到同步队列中去。
             *      然后内部unLock()的时候，就会去唤醒一个同步队列中的线程
             */
            String result1 = blockingQueue.take();
            System.out.println(result1);

            /**
             *  取元素并移除元素 2
             *  poll()方法的第一种：与take()方法的区别是：如果队列为空，poll()方法不会阻塞，而是返回一个null。
             *       返回null，表示获取失败
             *
             *
             *  poll()方法的第二种：允许带有超时限制。如果指定时间内没有获取到元素的话，也会返回null
             *
             *
             *  然后每一次成功插入，都会notFull.signal()，发一个信号。将notFull条件队列中的一个线程迁移到同步队列中去。
             *      后内部unLock()的时候，就会去唤醒一个同步队列中的线程
             *
             */
            String result2 = blockingQueue.poll();
            System.out.println(result2);

            String result3 = blockingQueue.poll(10,TimeUnit.MILLISECONDS);
            System.out.println(result3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BlockingQueueDemo blockingQueueDemo  = new BlockingQueueDemo();
        blockingQueueDemo.blockedQueueTest();
    }

}
