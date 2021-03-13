package util.study.gupao.thread.pool;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/13 17:45
 * @File : WorkerDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class WorkerDemo implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
