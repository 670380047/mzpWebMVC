package util.study.thread.jdk8.juc.question;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 16:49
 * @File : TestAbcAlternateByObj
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestAbcAlternateByObj {

    public static void main(String[] args) {
        AlternateByObj alternate = new AlternateByObj();
        /**
         * 定义lambda表达式写Runnable. 再下面重复使用
         */
//        Runnable runnable = ()->{
//            for(int i =1;i<=20;i++){
//                alternate.loopA(i);
//            }
//            System.out.println("-------------------------------");
//        };

        /**
         * 线程A打印
         */
        new Thread( ()->{
            for(int i =1;i<=20;i++){
                alternate.loopA(i);
            }
        },"线程A-1").start();
    }
}

class AlternateByObj{
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Alternate类的finalize方法执行了");  //好像并没有执行
    }

//    Lock lock = new ReentrantLock();
    /**
     * 一个lock可以有多个条件Condition。
     *  然后：各自的等待只能由各自的唤醒来唤醒
     *      condition1.await只能由condition1.signal/condition1.signalAll来唤醒
     *      condition2.await只能由condition2.signal/condition1.signalAll来唤醒
     *      condition2.await只能由condition2.signal/condition1.signalAll来唤醒
     */
//    Condition condition1 = lock.newCondition();
//    Condition condition2 = lock.newCondition();
//    Condition condition3 = lock.newCondition();
    Object obj1 = new Object();
    Object obj2 = new Object();
    Object obj3 = new Object();

    private int number = 1; // 当前正在执行的线程的标记（默认等于1，表示A线程。2=B线程。 3=C线程）

    /**
     * 循环打印A
     * @param totalLoop   表示第几轮循环
     */
    public void loopA(int totalLoop){
        synchronized (obj1){        //使用obj1的对象锁来锁定代码块。在代码块内部只能使用obj1对应的wait()、notify()等方法
            /**
             * 1.判断是不是当前线程需要执行。
             *      如果不是，就等待。
             */
            while(number != 1){
                try {
                    System.out.println("等待");
                    obj1.wait();
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
            /**
             * 2.打印
             */
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t第"+totalLoop+"轮");
            }
            System.out.println("-------------------------------");
            /**
             * 3.唤醒其他线程
             */
            number = 2;
            obj1.notifyAll();
//            obj2.notifyAll();     // 报错。在obj1对象锁的代码块里面，如果使用obj2对象的notifyAll()之类的方法时，就会报错“java.lang.IllegalMonitorStateException”
        }

    }

}