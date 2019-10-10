package util.thread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/9/25 20:42
 * @File : MyThread
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/9/25 20:42
 */
public class MyThread extends Thread {
    private String myName;



    public static void main(String[] args) {
        //这个参数并不是线程的名字。需要Thread.currentThread().setName()设置才行
        MyThread myThread1 = new MyThread("1(小明)");
        MyThread myThread2 = new MyThread("2（小红）");
        MyThread myThread3 = new MyThread("3（小刚）");
        MyThread myThread4 = new MyThread("4（小李）");
        MyThread myThread5 = new MyThread("5（小叶）");
        //调用start()的顺序，并不是线程接下来运行的顺序。  线程运行的顺序完全有cpu决定
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();

    }

    public MyThread() {
    }

    public MyThread(String myName) {
        this.myName = myName;
        //这个setName()方法是线程的方法。作用是设置线程的名字
        this.setName(myName);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("MyThread类启动"+myName);
        //设置线程的名字。这里直接用this.myName而不是用get方法：是因为每个构造方法传进来的参数，都设置了各自的myName
        //这个方法可以放在构造方法里面。比如：this.setName(myName);
//        Thread.currentThread().setName(this.myName);
        System.out.println("当前线程："+Thread.currentThread().getName());
        try {
            for(int i =0;i<5;i++){
                int time = (int) (Math.random()*2000);
                Thread.sleep(time);
                System.out.println("run="+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
}
