package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式
 *      java笔记实现类
 *      java产品族中的一个产品（产品结构）：笔记
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:09
 * @File : JavaNote
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JavaNote implements INote{

    @Override
    public void edit() {
        System.out.println("记录java笔记");
    }
}
