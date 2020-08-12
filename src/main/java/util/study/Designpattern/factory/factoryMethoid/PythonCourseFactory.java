package util.study.Designpattern.factory.factoryMethoid;

/**
 * 工厂方法模式：
 *      创建python课程工厂
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 22:46
 * @File : PythonCourseFactory
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class PythonCourseFactory implements ICourseFactory{
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
