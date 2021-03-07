package util.study.gupao.thread.deadLock;

/**
 * 测试死锁。==》DeadLockDemo类中
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/1 16:23
 * @File : Account
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Account {
    private String accountName;
    private int balance;

    public Account(String accountName, int balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public void debit(int amount){ //更新转出方的余额
        this.balance -= amount;
    }

    public void credit(int amount) { //更新转入方的余额
        this.balance += amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
