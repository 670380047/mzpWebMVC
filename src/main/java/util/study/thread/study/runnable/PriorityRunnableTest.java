package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 16:31
 * @File : PriorityRunnableTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程优先级测试类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 16:31
 */
public class PriorityRunnableTest {
    public static void main(String[] args) {
        PriorityRunnable priorityRunnable = new PriorityRunnable();
        Thread t1 = new Thread(priorityRunnable);
        /**
         * 设置t1线程的优先级为10
         */
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();

        /**
         * 设置当前线程的优先级
         */
        Thread.currentThread().setPriority(1);
        for(int i=100;i<200;i++){
            System.out.println(Thread.currentThread().getName()+"的优先级："+
                    Thread.currentThread().getPriority()+"--"+i);
        }

    }
}
