package util.thread.test.isAlive;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/10 20:39
 * @File : ThreadIsAliveRun
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/10/10 20:39
 */
public class ThreadIsAliveRun {
    public static void main(String[] args) throws InterruptedException {
        IsAlive isAlive = new IsAlive(" 'mzp的isAlive' ");
        //线程处于“运行状态”或“准备运行状态（就绪状态，等待cpu分配）”
        System.out.println("线程启动前=======是否处于活动状态："+isAlive.isAlive());
        isAlive.start();
        Thread.sleep(1000);
        System.out.println("线程启动后=======是否处于活动状态："+isAlive.isAlive());
    }
}
