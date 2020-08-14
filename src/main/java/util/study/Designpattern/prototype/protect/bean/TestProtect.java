package util.study.Designpattern.prototype.protect.bean;

/**
 * 测试protected的规则。
 *
 *      1.protected类型对“包内成员可见、对子类可见”；
 *      2.若子类和基类不在同一包中，那么在子类中，子类实例可以访问其中基类“继承”过来的protected方法，
 *          但是不能访问基类实例的protected方法。可以理解为（从父类继承过来的protected成员，在子类中变为了私有的,因此在其他类中不能访问。）
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 20:40
 * @File : TestProtect
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestProtect {

    protected String myName = "mzp";
    protected void protectMethod(){
        System.out.println("TestProtect类中的受保护方法");
    }
}
