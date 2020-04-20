package util.study.thread.test.isAlive;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/10 20:34
 * @File : IsAlive
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.jetbrains.annotations.NotNull;

/**
 * 多线程，测试isAlive
 * @author maozp3
 * @description:
 * @date: 2019/10/10 20:34
 */
public class IsAlive extends Thread{

    public IsAlive() {
    }

    public IsAlive(@NotNull String name) {
        super(name);
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("线程启动中=======是否处于活动状态："+this.isAlive());
        System.out.println("线程"+currentThread().getName()+"启动了");
    }
}
