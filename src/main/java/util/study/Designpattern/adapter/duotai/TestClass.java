package util.study.Designpattern.adapter.duotai;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/18 18:10
 * @File : TestClass
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 测试多态
 * 3.继承了抽象方法的普通方法。 属于第三等级
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/18 18:10
 */
public class TestClass extends TestAbstract {
    @Override
    void eat() {
        System.out.println("TestClass的eat方法（eat方法的根源在TestAbstract抽象类中）");
    }

    @Override
    public void say() {
        System.out.println("TestClass的say方法（say方法的根源在TestInterface接口中）");
    }
}
