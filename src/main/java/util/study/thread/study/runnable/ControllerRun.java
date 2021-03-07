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

    private volatile boolean flag =true;

    @Override
    public void run() {
        while(flag){
            System.out.println(Thread.currentThread().getName()+"线程睡眠5秒.");
        }
        try {
            System.out.println("线程内部查看中断标志："+Thread.currentThread().isInterrupted());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"我会不会在还行？ 答案，不会");
        } catch (InterruptedException e) {

        e.printStackTrace();
        }

    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
