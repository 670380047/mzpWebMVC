package util.study.Designpattern.factory.factoryMethoid;

/**
 * 工厂方法模式：
 *      创建java课程的工厂
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 22:45
 * @File : JavaCourseFactory
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JavaCourseFactory implements ICourseFactory{
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
