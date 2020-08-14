package util.study.Designpattern.prototype.protect;

import util.study.Designpattern.prototype.protect.bean.TestProtect;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 20:43
 * @File : SubTestProtect
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SubOutPackage extends TestProtect {

    public static void main(String[] args) {
        new SubOutPackage().test();

    }

    /**
     * 测试方法
     */
    public void test(){
        // 和父类不再包中时，即受保护的方法不在同一个包中。
        // 调用父类中的Protect方法，这个方法是从父类继承过来的。因为这个是从父类继承过来的
        protectMethod();
        super.protectMethod();
        System.out.println(myName);

        // 和父类不再包中时，即受保护的方法不在同一个包中。
        // 不允许调用“父类实例”的受保护方法和属性。
//        new TestProtect().protectMethod();             //编译报错
//        System.out.println(new TestProtect().myName);     //编译报错

        // 和父类不再包中时，即受保护的方法不在同一个包中。
        // 不允许调用“其他实例”的受保护方法和属性（这个方法和属性的来源都是继承自他们的父类）。
//        new SubInnerPackage().protectMethod();        //编译报错
//        System.out.println(new SubInnerPackage().myName);     //编译报错
    }
}
