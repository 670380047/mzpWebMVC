package util.study.Designpattern.factory.simpleFactory;

/**
 * 简单工厂模式；python课程
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 19:37
 * @File : PythonCourse
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class PythonCourse implements ICourse{
    @Override
    public void getCourse() {
        System.out.println("python课程");
    }
}
