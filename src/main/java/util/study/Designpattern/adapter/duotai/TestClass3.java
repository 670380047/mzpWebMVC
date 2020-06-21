package util.study.Designpattern.adapter.duotai;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/19 19:09
 * @File : TestClass3
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 测试。抽象类TestClass3继承普通类TestClass2
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/19 19:09
 */
public abstract class TestClass3 extends TestClass2{
//    public TestClass3() {
//    }
    public void walk(){
        System.out.println("TestClass3的walk方法（根源在TestClass3抽象类中）");
    }
    public abstract void hello();
}
