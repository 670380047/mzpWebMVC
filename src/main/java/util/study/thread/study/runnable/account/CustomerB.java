package util.study.thread.study.runnable.account;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 16:45
 * @File : CustomerB
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程通信银行场景测试类:客户A
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 16:45
 */
public class CustomerB implements Runnable{
    /**
     * 银行账户
     */
    private Account account;

    public CustomerB() {
    }

    public CustomerB(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
            account.withdraw(1000);
        }
    }



    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
