package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 11:11
 * @File : TicketTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 继承Thread类。对比实现Runnable接口
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 11:11
 */
public class TicketTest {
    public static void main(String[] args) {
        /**
         * 只创建了一个实例。（对比继承Thread类）
         * 对比继承Thread的方式，这里实现了对变量的共享（变量不用加static），因为创建了一个接口实现类的实例，
         * 下面是对同一个接口实现类实例的操作
         */
        Ticket ticket = new Ticket();

        /**
         * 多个Thread对象都是对同一个ticket对象做的处理。实现对ticket中变量的共享，而且ticket中不用加static
         */
        Thread win1 = new Thread(ticket,"1号窗口");
        win1.start();
        Thread win2 = new Thread(ticket,"2号窗口");
        win2.start();
        Thread win3 = new Thread(ticket,"3号窗口");
        win3.start();
    }
}
