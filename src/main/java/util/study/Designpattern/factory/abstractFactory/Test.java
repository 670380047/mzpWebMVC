package util.study.Designpattern.factory.abstractFactory;

/**
 * 抽象工厂模式
 *       测试类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/9 0:16
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    public static void main(String[] args) {
        // 创建一个java的工厂
        Factory javaFactory = new JavaFactory();
        // 创建一个java的课程，然后获取课程信息
        javaFactory.createCourse().getCourse();
        //创建一个java的笔记，然后编辑
        javaFactory.createNote().edit();
        //创建一个java的视频，然后录制
        javaFactory.createVideo().record();

        System.out.println("-------------------------------------------");

        // 创建一个python的工厂
        Factory pythonFactory = new PythonFactory();
        // 创建一个python的课程，然后获取课程信息
        pythonFactory.createCourse().getCourse();
        //创建一个python的笔记，然后编辑
        pythonFactory.createNote().edit();
        //创建一个python的视频，然后录制
        pythonFactory.createVideo().record();
    }
}
