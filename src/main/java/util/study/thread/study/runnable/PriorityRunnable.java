package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 16:30
 * @File : PriorityRunnable
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程优先级实现类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 16:30
 */
public class PriorityRunnable implements Runnable{

    int i = 0;

    @Override
    public void run() {
        while(i<100){
            System.out.println(Thread.currentThread().getName()+"的优先级："+
                    Thread.currentThread().getPriority()+"--"+i);
            i++;
        }
    }
}
