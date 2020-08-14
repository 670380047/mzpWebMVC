package util.study.Designpattern.builder;

/**
 * 【建造者模式】
 *      建造者接口：规范
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/14 15:37
 * @File : IBuilder
 * @Software: IntelliJ IDEA 2019.2.04
 */
public interface IBuilder {
    //设置属性
    IBuilder addCourse(String name);
    IBuilder addVideo(String video);
    IBuilder addNote(String note);
    IBuilder addPpt(String ppt);
    //构建对象的过程
    ICourse build();

}
