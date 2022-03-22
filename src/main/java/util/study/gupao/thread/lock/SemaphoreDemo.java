package util.study.gupao.thread.lock;

import java.util.concurrent.Semaphore;

/**
 *  Semaphore信号。是AQS的一种共享锁
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/11 0:59
 * @File : SemaphoreDemo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        /**
         * 定义5个通行证。 即，最多允许同时5个线程执行临界区
         */
        Semaphore semaphore=new Semaphore(5); //令牌数 state=5
        for(int i=0;i<10;i++){
            new Car(semaphore,i).start();
        }
    }
    static class Car extends Thread{
        Semaphore semaphore;
        int num;

        public Car(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }
        @Override
        public void run() {
            try {
                /**
                 * 获取通行证
                 */
                semaphore.acquire(); //5-1 获得令牌.(没拿到令牌，会阻塞，拿到了就可以往下执行）

                System.out.println("第"+num+"线程占用一个令牌");
                Thread.sleep(3000);
                System.out.println("第"+num+"线程释放一个令牌");
                /**
                 * 释放通行证
                 */
                semaphore.release(); //释放令牌
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}