package util.study.Designpattern.factory.abstractFactory;

/**
 *
 * 抽象工厂模式
 *      python工厂实现类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:15
 * @File : PythonFactory
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class PythonFactory extends Factory{
    @Override
    protected ICourse createCourse() {
        //调用父类抽象函数中的初始化信息
        super.init();
        //实例化对象
        return new PythonCourse();
    }

    @Override
    protected IVideo createVideo() {
        //调用父类抽象函数中的初始化信息
        super.init();
        //实例化对象
        return new PythonVideo();
    }

    @Override
    protected INote createNote() {
        //调用父类抽象函数中的初始化信息
        super.init();
        //实例化对象
        return new PythonNote();
    }
}
