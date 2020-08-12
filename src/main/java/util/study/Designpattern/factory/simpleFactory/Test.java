package util.study.Designpattern.factory.simpleFactory;

/**
 * 测试类。
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 19:43
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {

    public static void main(String[] args) {
        //创建一个工厂实例
        CourseFactory factory =  new CourseFactory();
        // 调用工厂中创建实例的方法，传入不同的类型，来创建实例
        ICourse course1 = factory.createBean(JavaCourse.class);  //向工厂中传入JavaCourse.class
        course1.getCourse(); // java课程

        ICourse course2 = factory.createBean(PythonCourse.class);  //向工厂中传入PythonCourse.class
        course2.getCourse(); // python课程
    }
}
