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

    /**
     * 静态方法也可以继承.但不存在重写的情况。只存在隐藏(如果你在子类中又定义了一个相同签名的静态方法，那么父类的静态方法将被隐藏，而不是重写)
     */
    public static void myStatic(){
        System.out.println("TestClass2类的myStatic静态方法（来自于TestClass2类）");
    }

    @Override
    void eat() {
        System.out.println("TestClass2的eat方法（eat方法的根源在TestAbstract抽象类中）");
    }

    void wangFly(){
        System.out.println("TestClass2的wangFly方法（TestClass2自己的方法）");
    }

    TestClass mySelf(){
        System.out.println("this可以当做一个对象，返回给一个对应类型变量。 " +
                "super关键字不行。super关键字只是告诉编译器：应该去调用父类的某个方法。super本身并不像this那样指向了一个对象");
        return this;
    }

    @Override
    public String toString() {
        return "TestClass2{" +
                "name='" + name + "',"+
                "score="+score+","+
                "sex='"+sex+"',"+
                "age="+this.getAge()+  // 这个getAge()是继承自父类TestClass的
                "}";

    }
}
