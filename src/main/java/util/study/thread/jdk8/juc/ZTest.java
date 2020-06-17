package util.study.thread.jdk8.juc;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 16:11
 * @File : ZTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * juc目录说明
 * 一、原子变量  ---》AtomicDemo类中
 * 二、volatile关键字 和  内存可见性说明  ---》TestVolatile类中
 * 三、CAS算法 ---》TestCAS类中
 * 四、闭锁的概念 ---》TestCountDownLatch类中
 * 五、解决多线程安全问题的集中方式： ---》TestLock类中
 *      1.同步代码快
 *      2.同步方法
 *      3.同步锁lock
 * 六、这个是测试线程池的写法的、并且测试把List、Map、set转换为同步。---》CopyOnWriteList类中
 * 七、处理生产者消费者问题（线程通信）中的“虚假唤醒”       --->TestProducer类中
 * 八、lock.lock() 显式锁处理生产者消费者问题（线程通信）   ---》product/lock/TestProducerLock类中
 *    显式锁创建：
 *       private Lock lock = new ReentrantLock();
 *    等待/唤醒的条件类创建：
 *       private Condition condition=lock.newCondition();
 *    显式锁的条件类使用： 用来实现线程通信.
 *         Condition类和Object类对比
 *         await = wait
 *         signal = notify
 *         signalAll = notifyAll
 *       condition.await()  condition.signal()   condition.signalAll()
 * 九、线程按序交替。    ---》question/TestAbcAlternate类中
 *      线程通信。多个lock的多个Condition互相唤醒。（轮流打印ABC）
 * 十、线程8锁       ---》product/TestThread8Monitor类中
 *        1.关键点：非静态方法的锁为（默认）：this     （对象锁）
 *                静态方法的锁为（默认）：类名.class   （类锁）
 *        2.关键点：在某一时刻，只能有一个线程获取到锁。
 *                对象锁和类锁都是一样的，一个线程获取了这把锁，其他线程就获取不到
 * 十一、线程池。作用，避免频繁的创建和销毁线程资源。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 16:11
 */
public class ZTest {
}
