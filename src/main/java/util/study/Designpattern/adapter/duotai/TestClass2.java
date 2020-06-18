package util.study.Designpattern.adapter.duotai;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/18 18:15
 * @File : TestClass2
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 测试多态
 * 4.继承了第三等普通类的一个普通类。属于第四等
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/18 18:15
 */
public class TestClass2 extends TestClass {

    @Override
    void eat() {
        System.out.println("TestClass2的eat方法（eat方法的根源在TestAbstract抽象类中）");
    }

    void wangFly(){
        System.out.println("TestClass2的wangFly方法（自己的方法）");
    }
}
