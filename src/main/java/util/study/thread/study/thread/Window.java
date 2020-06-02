package util.study.thread.study.thread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 10:45
 * @File : Window
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 10:45
 */
public class Window extends Thread{
    /**
     * 定义为static，保证win1  win2 win3 单个对象共享这一个独享属性。
     */
    static int tick = 100;

    /**
     * run()方法是：线程执行体
     */
    @Override
    public void run() {
        while(tick > 0){
            System.out.println(Thread.currentThread().getName()+"完成售票，余票为"+ --tick);
        }
    }
}
