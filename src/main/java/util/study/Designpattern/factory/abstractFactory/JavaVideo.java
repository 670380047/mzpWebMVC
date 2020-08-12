package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式
 *      java视频实现类
 *      java产品族中的一个产品（产品结构）：视频
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:08
 * @File : JavaVideo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JavaVideo implements IVideo{
    @Override
    public void record() {
        System.out.println("录制java视频");
    }
}
