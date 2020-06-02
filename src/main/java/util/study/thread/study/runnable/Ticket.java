package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 11:10
 * @File : Ticket
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 11:10
 */
public class Ticket implements Runnable{
    int tick = 100;

    @Override
    public void run() {
        while(tick > 0){
            System.out.println(Thread.currentThread().getName()+"完成售票，余票为"+ --tick);
        }

    }
}
