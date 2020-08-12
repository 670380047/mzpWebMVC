package util.study.Designpattern.factory.factoryMethoid;

/**
 * 工厂方法模式
 *      测试类
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 22:47
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    public static void main(String[] args) {
        //new一个java工厂类。  用工厂规范接口去变量去承接
        ICourseFactory factory1 = new JavaCourseFactory();
        // 调用工厂类的create方法创建实例。 调用的是具体实现类的方法
        ICourse java = factory1.create();
        //查看创建的对象
        java.getCourse();

        //new一个python工厂类。  用工厂规范接口去变量去承接
        ICourseFactory factory2 = new PythonCourseFactory();
        // 调用工厂类的create方法创建实例。 调用的是具体实现类的方法
        ICourse python = factory2.create();
        //查看创建的对象
        python.getCourse();
    }
}
