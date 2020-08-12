package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式：
 *      抽象工厂类。提供规范，做一些默认操作
 *      就是“产品族”的抽象：定义一个产品族中应该有哪些产品（产品结构）
 *      他的实现类就是具体的产品族了。比如java产品族
 *
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:01
 * @File : Factory
 * @Software: IntelliJ IDEA 2019.2.04
 */
public abstract class Factory {
    public void init(){
        System.out.println("初始化一些基础数据....");
    }
    //创建课程
    protected abstract ICourse createCourse();
    //录制视频
    protected abstract IVideo createVideo();
    //记录笔记
    protected abstract INote createNote();
}
