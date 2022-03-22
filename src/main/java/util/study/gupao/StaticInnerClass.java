package util.study.gupao;

/**
 *  静态内部类中的作用域
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/10 18:15
 * @File : StaticInnerClass
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class StaticInnerClass {
    private static String name = "mzp";
    private String num = "83510";

    // 静态内部类可以用public,protected,private修饰
    static class Person {
        // 静态内部类中可以定义静态或者非静态的成员
        private String address = "China";
        private static String sex = "男";

        // 内部类公有成员
        public String mail = "670380047@qq.com";

        public void display() {
            // System.out.println(num);//不能直接访问外部类的非静态成员

            // 静态内部类不能访问外部类的非静态成员(包括非静态变量和非静态方法)
            System.out.println(name);// 只能直接访问外部类的静态成员

            // 静态内部类只能访问外部类的静态成员(包括静态变量和静态方法)
            System.out.println("Inner " + address);// 访问本内部类成员。
            // 静态内部类可以访问自己的非静态成员
            System.out.println("性别：" + sex);// 访问本内部类成员。
        }
    }

    /**
     * 这里是外部类的方法
     */
    public void printInfo() {
        Person person = new Person();

        // 外部类访问内部类的非静态成员:实例化内部类即可
        person.display();
        person.address = "中国";  // 外部类可以通过对象实例来访问内部类的成员方法
        // System.out.println(mail);//不可访问
        // System.out.println(address);//不可访问
        System.out.println(person.address);// 可以访问内部类的私有成员

        System.out.println(Person.sex);// 外部类访问内部类的静态成员：内部类.静态成员
        System.out.println(person.mail);// 可以访问内部类的公有成员
    }

    public static void main(String[] args) {
        StaticInnerClass staticTest = new StaticInnerClass();
        staticTest.printInfo();
    }
}
