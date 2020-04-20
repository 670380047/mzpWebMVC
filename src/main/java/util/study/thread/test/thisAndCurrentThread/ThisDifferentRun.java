package util.study.thread.test.thisAndCurrentThread;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/24 20:06
 * @File : ThisDifferentRun
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/10/24 20:06
 */
public class ThisDifferentRun {
    public static void main(String[] args) {
        //实例化一个自定义的线程
        ThisDifferent thisDiff = new ThisDifferent();
        //把自定义的线程放在另一个新线程的构造方法里面（主要问题所在）:
        //1.在下面的自定义线程中（run方法中），Thread.currentThread()指的是这个new Thread(**),即t1，是一个新的线程。
        //2.而this指的是这个构造参数中传入的对象，即thisDiff，是我们自定义的线程。
        //3.所以Thread.currentThread()和this不相等（==）。（另外Thread.currentThread()获取的是执行这段代码的线程）
        //因为，把自定义的线程thisDiff当做构造方法传入new Thread之后，被赋值给线程内部的一个私有属性叫做target，
        //   然后调用run方法的时候，其实调用的是target.run(),也就是我们自定义的线程，也就是this
        Thread t1 = new Thread(thisDiff);
        System.out.println("main方法-----------start。 t1 isAlive="+t1.isAlive());
        System.out.println("t1.getName()="+t1.getName());
        System.out.println("获取线程的唯一标识：t1.getId()="+t1.getId());
        t1.setName("t1");
        t1.start();
        System.out.println("main方法-----------end。 t1 isAlive="+t1.isAlive());

    }
}
