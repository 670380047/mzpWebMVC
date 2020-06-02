package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 10:58
 * @File : PrimeRun
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *  创建线程的方式二： --PrimeRunTest类中
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 10:58
 */

/**
 * 7.1 声明一个类，实现Runnable接口
 */
public class PrimeRun implements Runnable{
    /**
     * 7.2 实现接口中的run()方法，同时编写线程执行体
     */
    @Override
    public void run() {
        for(int i = 0;i<100;i++){
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
