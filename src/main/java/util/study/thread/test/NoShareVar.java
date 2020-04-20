package util.study.thread.test;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/9/27 19:27
 * @File : NoShareVar
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/9/27 19:27
 */
public class NoShareVar extends Thread{
    private int count = 5;

    public NoShareVar() {
        System.out.println("构造方法"+this.getName()+"被线程"+Thread.currentThread().getName()+"打印:");
    }

    public NoShareVar(String name){
        //设置线程名字
        this.setName(name);
        System.out.println("有参构造方法"+this.getName()+"被线程"+Thread.currentThread().getName()+"打印:");
    }

    //线程"不共享"数据
//    @Override
//    public void run() {
//        super.run();
//        while(count > 0){
//            System.out.println("由"+this.currentThread().getName()
//            +"线程来计算，count="+count);
//            count --;
//        }
//    }

    //线程多个线程调用同一个线程的run方法。会产生线程不安全
    @Override
    public void run() {
        super.run();
        //比如，这个操作会分成3部分：1.取count的值；2.计算count-1; 3.给count赋值。 但在这3个过程中有多个线程都在跑
        count --;
        System.out.println("由"+this.currentThread().getName()
        +"线程来计算，count="+count);
    }

    //线程同步  synchronized
//    @Override
//    synchronized  public void run() {
//        super.run();
//        count --;
//        //this.getName()获取的是这个run()方法所属线程的名字。  而不是调用这个run()方法的线程的名字
//        //当一个线程（this.getName）被另一个线程（this.currentThread().getName()）调用的时候，这两个会不同。
//        System.out.println("this.getName="+this.getName());
//        System.out.println("   线程"+this.getName()+"由"+this.currentThread().getName()
//        +"线程来计算，count="+count);
//    }

}
