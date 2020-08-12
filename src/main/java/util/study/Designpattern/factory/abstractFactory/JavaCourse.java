package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式；java课程的实现类
 *      java产品族中的一个产品（产品结构）：课程
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 19:18
 * @File : JavaCourse
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JavaCourse implements ICourse {

    public JavaCourse() {
        System.out.println("创建java课程");
    }

    @Override
    public void getCourse() {
        System.out.println("获取java课程信息");
    }
}
