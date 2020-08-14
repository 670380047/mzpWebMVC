package util.study.Designpattern.builder;

/**
 * 【建造者模式】
 *      java课程的实现类。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/14 15:40
 * @File : javaCourse
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class JavaCourse implements ICourse{
    private String courseName;
    private String video;
    private String note;
    private String ppt;


    @Override
    public String toString() {
        return "javaCourse{" +
                "courseName='" + courseName + '\'' +
                ", video='" + video + '\'' +
                ", note='" + note + '\'' +
                ", ppt='" + ppt + '\'' +
                '}';
    }


    @Override
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getVideo() {
        return video;
    }

    public String getNote() {
        return note;
    }

    public String getPpt() {
        return ppt;
    }
}
