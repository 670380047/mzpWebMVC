package util.study.Designpattern.prototype.protect.bean;

import util.study.Designpattern.prototype.protect.SubOutPackage;

/**
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 21:01
 * @File : TestInner
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestInner {
    public static void main(String[] args) {
        // 同一个包内，跟受保护方法在同一个包下。
        // 允许调用其他对象的受保护方法和属性。（protected方法包内可见）
        new TestProtect().protectMethod();
        System.out.println(new TestProtect().myName);;

        // 同一个包内，跟受保护方法在同一个包下。
        // 允许调用其他对象(类在同一个包内)的受保护方法和属性。（protected方法包内可见）
        new SubInnerPackage().protectMethod();
        System.out.println(new SubInnerPackage().myName);

        // 同一个包内，跟受保护方法在同一个包下。
        // 允许调用其他对象(类不在同一个包内)的受保护方法和属性。（protected方法包内可见）
        new SubOutPackage().protectMethod();
        System.out.println(new SubOutPackage().myName);
    }
}
