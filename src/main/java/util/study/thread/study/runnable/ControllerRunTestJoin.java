package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 15:44
 * @File : ControllerRunTestJoin
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程控制。join()/join(long millis)
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 15:44
 */
public class ControllerRunTestJoin {
    public static void main(String[] args) {
        ControllerRun controllerRun = new ControllerRun();
        /**
         * 新建状态。
         */
        Thread t1 = new Thread(controllerRun,"控制1");
        System.out.println("t1.start()执行前：isAlive() = "+ t1.isAlive());
        /**
         * 就绪状态。之后获取cpu在运行的过程中是：运行状态
         */
        t1.start();
        System.out.println("t1.start()执行后：isAlive() = "+ t1.isAlive());

        try {
            /**
             * t1.join() 方法是告诉当前（main）线程。我t1要执行完之后，你（main）才能执行。
             * t1.join()也就是告诉t1线程的调用者（这里是main线程），t1要执行完毕，调用者才能继续。
             */
//            t1.join();

            /**
             * join()调用完之后，就会从“运行状态”进入“阻塞状态”
             * t1.join(10) 告诉当前线程（main线程），我t1在这里要执行10毫秒，10毫秒结束后，你再执行
             * （如果10毫秒后，t1没有执行完毕，那么t1和main线程还会继续“抢占式”争夺cpu）
             */
            t1.join(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<5;i++){
            /**
             * yield()调用完之后，就会从“运行状态”重新回到“就绪状态”。 yield()方法不会释放锁
             */
//            Thread.currentThread().yield();
            System.out.println(Thread.currentThread().getName()+":"+i);
        }

    }
}
