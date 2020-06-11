package util.study.thread.study.runnable.product;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 17:05
 * @File : Clerk
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 生产者消费者问题。 店员
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 17:05
 */
public class Clerk {
    /**
     * 商品数量
     */
    private  int product;

    public Clerk() {
        System.out.println("clerk父类");
    }

    public Clerk(int product) {
        this.product = product;
    }

    /**
     * 进货
     */
    public synchronized void addProduct(){
        if(product < 20){
            System.out.println(Thread.currentThread().getName()+"生产者生产了第"+product++ +"个产品");
            notifyAll();
        }else{
            System.out.println(Thread.currentThread().getName()+"货物已满！");
            try {
                /**
                 * 非静态同步方法。方法上的同步锁是 this。
                 */
                this.wait();
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
    }

    /**
     * 卖货
     */
    public synchronized void saleProduct(){
       if(product > 0){
           System.out.println(Thread.currentThread().getName()+"消费者消费了第"+product-- +"个产品");
           notifyAll();
       }else {
           System.out.println(Thread.currentThread().getName()+"缺货！");
           try {
               /**
                * 非静态同步方法。方法上的同步锁是 this。
                */
               this.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
