package util.study.thread.study.runnable.product;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/3 9:26
 * @File : Test
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 继承关系。调用子类构造方法是，他会往上先依次调用父类的构造方法。从上往下来实例化
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/3 9:26
 */
public class Test extends Clerk{
    public Test() {
        System.out.println("test子类");
    }
}
