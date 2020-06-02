package util.study.thread.study.runnable.product;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 17:12
 * @File : Customer
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 生产者消费者问题。 消费者
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 17:12
 */
public class Customer implements Runnable{
    /**
     * 店员
     */
    private Clerk clerk;

    public Customer() {
    }

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true){
            clerk.saleProduct();  //消费者不断地从店员那里买货
        }
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }
}
