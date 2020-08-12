package util.study.Designpattern.factory.abstractFactory;

/**
 * 【工厂模式】：抽象工厂模式
 *      课程总接口，表示：课程的规范
 *      （产品结构：就是一个产品）
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 19:17
 * @File : ICourse
 * @Software: IntelliJ IDEA 2019.2.04
 */
public interface ICourse {
    // 获取课程信息
    void getCourse();
}
