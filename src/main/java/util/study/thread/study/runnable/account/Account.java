package util.study.thread.study.runnable.account;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 16:38
 * @File : WaitAccount
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程通信银行场景测试类:账户
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 16:38
 */
public class Account {
    /**
     * 余额
     */
    private double balance;

    /**
     * 用来做同步监听器。锁。 （这里是对象锁，区别的还要一种叫类锁）
     */
    Object obj = new Object();

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    /**
     * 存款操作
     * @param amount
     */
    public void deposit(double amount){
        synchronized (obj){
            if(balance + amount <= 10000){
                balance += amount;
                System.out.println(Thread.currentThread().getName()+"成功存入："+amount+"。余额为："+balance);
                obj.notifyAll();
            }else{
                System.out.println(Thread.currentThread().getName()+"账户已满");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 取款操作
     * @param amount
     */
    public void withdraw(double amount){
        synchronized (obj){
            if(balance > amount){
                balance -= amount;
                System.out.println(Thread.currentThread().getName()+"成功取出："+amount+"。余额为："+balance);
                obj.notifyAll();
            }else {
                System.out.println(Thread.currentThread().getName()+"余额不足！");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        }
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
