package util.study.thread.study.runnable;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 15:17
 * @File : ControllerRunTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 线程控制测试类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 15:17
 */
public class ControllerRunTest {
    public static void main(String[] args) {
        ControllerRun controllerRun = new ControllerRun();
        Thread t1 = new Thread(controllerRun,"控制1");
        t1.start();
        System.out.println("t1.isAlive() = "+ t1.isAlive());
        /**
         * 只要线程还活着，就“中断他的阻塞状态”（唤醒他）。
         */
        while (t1.isAlive()){
            /**
             * interrupt()方法调用完之后，就会从“阻塞状态”变为“就绪状态”
             */
            t1.interrupt();
        }


    }
}
