package util.study.thread.study.runnable.account;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 16:43
 * @File : CustomerA
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程通信银行场景测试类:客户A
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 16:43
 */
public class CustomerA implements Runnable{
    /**
     * 银行账户
     */
    private Account account;

    public CustomerA() {
    }

    public CustomerA(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
            account.deposit(1000);
        }
    }



    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


}
