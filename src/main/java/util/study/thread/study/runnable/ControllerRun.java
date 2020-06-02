package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 15:15
 * @File : ContrellerRun
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程控制实现类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 15:15
 */
public class ControllerRun implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i<5;i++){
            System.out.println(Thread.currentThread().getName()+"线程睡眠1秒."+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                /**
                 * 睡眠的异常不做处理。这个异常就是用来唤醒线程的
                 * 他是当调用 interrupt()来中断阻塞状态的时候，是通过抛出一个InterruptedException异常来实现的。
                 * （这个异常不作处理，控制台就不打印这个消息了，看着扎眼）
                 * java.lang.InterruptedException: sleep interrupted
                 */
            e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"我是这是“线程控制”语句的."+i);
        }
    }
}
