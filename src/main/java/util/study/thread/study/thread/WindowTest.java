package util.study.thread.study.thread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/1 10:49
 * @File : WindowTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 继承Thread类。对比实现Runnable接口
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/1 10:49
 */
public class WindowTest {
    public static void main(String[] args) {
        /**
         * Window继承了Thread类。(对比实现Runnable方式)
         * 然后下面new了三个Window实例（win1,win2,win3），所以Window中的变量要加static才能实现单个不同的实例对同一个变量的共享，
         */
        Window win1 = new Window();
        Window win2 = new Window();
        Window win3 = new Window();

        win1.setName("1号窗口");
        win2.setName("2号窗口");
        win3.setName("3号窗口");
        win1.start();
        win2.start();
        win3.start();
    }
}
