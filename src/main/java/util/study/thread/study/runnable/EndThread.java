package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 11:27
 * @File : EndThread
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *  通知方式：
 * 结束线程测试的实现类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 11:27
 */
public class EndThread implements Runnable{
    int i = 0;

    public boolean flag = true;
    @Override
    public void run() {
        while (flag){
            i++;
            System.out.println(Thread.currentThread().getName()+":"+i);
            System.out.println(i);
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
