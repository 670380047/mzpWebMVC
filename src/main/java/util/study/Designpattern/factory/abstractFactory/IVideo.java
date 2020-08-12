package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式：
 *      视频类接口，规范
 *      （产品结构：就是一个产品）
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/8 23:57
 * @File : IView
 * @Software: IntelliJ IDEA 2019.2.04
 */
public interface IVideo {
    // 录制视频的功能
    void record();
}
