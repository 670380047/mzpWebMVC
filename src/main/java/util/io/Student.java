package util.io;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/19 12:24
 * @File : Student
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.io.Serializable;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/19 12:24
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -3763968750116334764L;
    private int studentNum;

    public Student() {}

    public Student(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNum=" + studentNum +
                '}';
    }
}
