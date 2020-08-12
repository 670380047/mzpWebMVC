package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式
 *      java工厂实现类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:10
 * @File : JavaFactory
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JavaFactory extends Factory{
    @Override
    protected ICourse createCourse() {
        //调用父类抽象函数中的初始化信息
        super.init();
        //创建实例
        return new JavaCourse();
    }

    @Override
    protected IVideo createVideo() {
        //调用父类抽象函数中的初始化信息
        super.init();
        //创建实例
        return new JavaVideo();
    }

    @Override
    protected INote createNote() {
        //调用父类抽象函数中的初始化信息
        super.init();
        //创建实例
        return new JavaNote();
    }
}
