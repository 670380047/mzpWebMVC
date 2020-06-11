package util.study.thread.study.runnable.product;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 17:03
 * @File : productor
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 生产者消费者问题。 测试类
 *      生产者(Producer)将产品交给店员(Clerk), 而消费者(Customer )从店员处取走产品，店员一次只能持有固定数量的产品(比如:20)，
 *      如果生产者试图生产更多的产品，店员会叫生产者停一下,如果店中有空位放产品了再通知生产者继续生产;如果店中没有产品了,
 *      店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *      这里可能出现两个问题:
 *      生产者比消费者快时，消费者会漏掉一些数据没有取到。
 *      消费者比生产者快时，消费者会取相同的数据。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 17:03
 */
public class TestProducer {
    public static void main(String[] args) {
        /**
         * 与本问题无关。
         * 为了测试继承关系。调用子类构造方法是，他会往上先依次调用父类的构造方法。从上往下来实例化
         */
//        Test test = new Test();

        Clerk clerk = new Clerk();
        Producer producerA = new Producer(clerk);
        Customer customerA = new Customer(clerk);

        Producer producerB = new Producer(clerk);
        Customer customerB = new Customer(clerk);

        new Thread(producerA,"生产者A").start();
        new Thread(customerA,"消费者A").start();
        new Thread(producerB,"生产者B").start();
        new Thread(customerB,"消费者B").start();
    }
}
