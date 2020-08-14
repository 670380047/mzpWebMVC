package util.study.Designpattern.prototype.protect;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 20:49
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class TestOuter {
    public static void main(String[] args) {
        // 不同包，跟受保护的方法不再同一个包下
        // 不允许调用其他类的受保护的方法。
//        new SubInnerPace().protectMethod();
//        System.out.println(new SubInnerPackage().myName);

        // 不同包，跟受保护的方法不再同一个包下
        // 不允许调用其他类的受保护的方法和属性。
//        new SubOutPackage().protectMethod();
//        System.out.println(new SubOutPackage().myName);;
    }

    /**
     * 测试protected属性的clone访问权限
     */
    public void testClone(){
        try {
            // 这个clone()是从父类Object继承过来的。在本类中可以使用。 下面两种方式是一样的。
            super.clone();
            clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // 这个Object实例的clone()方法在子类中不允许访问。
//        new Object().clone();
    }
}
