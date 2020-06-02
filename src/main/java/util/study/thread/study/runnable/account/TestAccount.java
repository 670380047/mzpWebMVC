package util.study.thread.study.runnable.account;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/2 16:46
 * @File : TestAccount
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程通信银行场景测试类: 测试类
 *  两个存，两个取
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/2 16:46
 */
public class TestAccount {
    public static void main(String[] args) {
        Account account = new Account();

        CustomerA customerA = new CustomerA(account);   // 存
        CustomerB customerB = new CustomerB(account);   // 取
        CustomerA customerC = new CustomerA(account);   // 存
        CustomerB customerD = new CustomerB(account);   // 取

        new Thread(customerA,"A").start();
        new Thread(customerB,"B").start();
        new Thread(customerC,"C").start();
        new Thread(customerD,"D").start();
    }
}
