package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式；python课程的实现类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 19:37
 * @File : PythonCourse
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class PythonCourse implements ICourse {
    public PythonCourse() {
        System.out.println("创建python课程");
    }

    @Override
    public void getCourse() {
        System.out.println("获取python课程信息");
    }
}
