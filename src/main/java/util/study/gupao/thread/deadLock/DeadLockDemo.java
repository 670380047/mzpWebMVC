package util.study.gupao.thread.deadLock;


/**
 *
 *  测试死锁：
 *      死锁发生的条件：下面四个要同时满足
 *      一、互斥。共享资源X和Y只能被一个线程占用
 *      二、占有且等待。线程T1已经取得共享资源X，在等待共享资源Y的时候，不释放资源X
 *      三、不可抢占。其他线程不能强行抢占线程T1占有的资源
 *      四、循环等待。线程T1等待线程T2占有的资源，线程T2等待线程T1占有的资源，就是循环等待。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/1 16:17
 * @File : DeadLockDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class DeadLockDemo implements Runnable{

    private Account fromAccount; //转出账户
    private Account toAccount; // 转入账户
    private  int amount;  //本次转账的金额
    private Allocate allocate;

    public DeadLockDemo(Account fromAccount, Account toAccount, int amount,Allocate allocate) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.allocate = allocate;
    }

    @Override
    public void run() {
        while (true) {
            /**
             * 这个apply()方法是一个同步方法，只允许一个对象调用，当只有通过这个apply()方法的时候，才可以去竞争锁。
             *
             *  相当于是加了一个独木桥，只有过了独木桥的人才能去竞争锁
             */
            if (allocate.apply(fromAccount, toAccount)) {
                try {
                    //因为两个线程传的账户是相反的。即在t1中的fromAccount，在t2中就是toAccount。
                    //如果没有外层的allocate调度的话，在第一个synchronized处t1抢到fromAccount，t2抢到toAccount，然后在第二个synchronized的时候，
                    //t1就该尝试去抢toAccount，t2尝试去抢fromAccount。结果都已经被对方抢走了。就会造成死锁
                    synchronized (fromAccount) {
                        synchronized (toAccount) {
                            if (fromAccount.getBalance() >= amount) {
                                fromAccount.debit(amount);
                                toAccount.credit(amount);
                            }
                        }
                    }
                    //转出账户的余额
                    System.out.println(fromAccount.getAccountName()+"->"+fromAccount.getBalance());
                    //转入账户的余额
                    System.out.println(toAccount.getAccountName()+"->"+toAccount.getBalance());
                } finally {
                    allocate.free(fromAccount,toAccount); //释放集合中的角色。
                }
            }

        }
    }

    public static void main(String[] args) {
        Account fromAccount = new Account("小毛",100000);
        Account toAccount = new Account("小段",100000);
        //创建一个调配者对象。只有调配成功的才可以去竞争锁
        Allocate allocate = new Allocate();

        Thread t1 = new Thread(new DeadLockDemo(fromAccount,toAccount,10,allocate));   //这里传的两个账户是相反的
        Thread t2 = new Thread(new DeadLockDemo(toAccount,fromAccount,30,allocate));   //这里传的两个账户是相反的
        t1.start();
        t2.start();
    }
}
