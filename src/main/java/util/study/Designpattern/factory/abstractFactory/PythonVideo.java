package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象方法模式
 *      python视频实现类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:12
 * @File : PythonVideo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class PythonVideo implements IVideo{
    @Override
    public void record() {
        System.out.println("记录python视频");
    }
}
