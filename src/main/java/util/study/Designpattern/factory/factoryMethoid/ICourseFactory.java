package util.study.Designpattern.factory.factoryMethoid;

/**
 * 工厂方法模式：
 *      工厂类的接口，表示：工厂的规范
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 22:39
 * @File : ICourseFactory
 * @Software: IntelliJ IDEA 2019.2.04
 */
public interface ICourseFactory {
    ICourse create();
}
