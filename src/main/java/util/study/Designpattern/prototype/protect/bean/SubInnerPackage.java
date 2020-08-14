package util.study.Designpattern.prototype.protect.bean;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 20:45
 * @File : SubInnerPackage
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SubInnerPackage extends TestProtect{

    public static void main(String[] args) {
        new SubInnerPackage().test();
    }

    public void test(){
        // 与父类在同一个包中时，跟受保护的方法在同一个包中。
        // 允许调用父类中的Protect方法和属性（从父类继承过来的）
        protectMethod();
        super.protectMethod();
        System.out.println(myName);
        // 与父类在同一个包中时，跟受保护的方法在同一个包中。
        // 允许调用父类实例的Protect方法和属性
        new TestProtect().protectMethod();
        System.out.println(new TestProtect().myName);
    }
}
