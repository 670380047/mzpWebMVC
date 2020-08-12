package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式
 *      python笔记实现类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:14
 * @File : PythonNote
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class PythonNote implements INote {
    @Override
    public void edit() {
        System.out.println("记录python笔记");
    }
}
