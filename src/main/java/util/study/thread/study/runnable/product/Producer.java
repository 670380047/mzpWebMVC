package util.study.thread.study.runnable.product;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 17:10
 * @File : Producer
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 生产者消费者问题。 生产者
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 17:10
 */
public class Producer implements Runnable{
    /**
     * 店员
     */
    private Clerk clerk;

    public Producer() {
    }

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true){
            clerk.addProduct();  //生产者不断的给店员送货
        }
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
    }


}
