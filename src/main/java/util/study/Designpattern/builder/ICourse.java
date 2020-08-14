package util.study.Designpattern.builder;

/**
 *【建造者模式】
 *      课程类接口：规范
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/14 15:31
 * @File : ICourse
 * @Software: IntelliJ IDEA 2019.2.04
 */
public interface ICourse {

    void setCourseName(String courseName);
    void setVideo(String video);
    void setNote(String note);
    void setPpt(String ppt);

}
