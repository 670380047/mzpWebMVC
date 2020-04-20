package util.study.io;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/19 11:15
 * @File : Person
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.io.Serializable;

/**
 * 对象序列化，专用类
 *      要被序列化的对象，他所包含的所有属性都需要支持序列化。
 *          基本类型等都支持：int、double
 *          String 、Integer等也支持，因为他们直接或间接实现了Serializable接口。
 *          自定义的类，需要自己去实现Serializable接口
 *      transient 关键字  和  static  关键字（static修饰的属性属于类了）  修饰的属性不能被序列化
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/19 11:15
 */
public class Person implements Serializable {
    /**
     * 序列号。将根据这个序列号去序列化。
     *      如果没有序列号，那么虚拟机将自动生成一个。序列化之后，此时如果改变对象中的一个属性的名字，那么将反序列化失败，因为又重新生成了一个序列号
     *      异常信息如下：java.io.InvalidClassException: util.study.io.Person; local class incompatible: stream classdesc serialVersionUID = 5415478303798314643, local class serialVersionUID = -6119261768914748355
     *
     *      如果我们写了序列号，序列化之后，此时如果改变对象中的一个属性的名字，反序列化不会失败，这个改变了名字的属性将会是空值，因为找不到原来对应的属性了，而且原来里面也没有这个新的属性名。
     *
     *      建议还是写上序列号。保证不会报错
     */
    private static final long serialVersionUID = -227600583788766568L;
    private String name;
    private Integer age;

    private Student student;    // 自定义的类，需要自己去实现Serializable接口

    private transient String nation; // transient 关键字表示该属性是临时的，不会被序列化

    private static String sex = "男"; // static 关键字修饰的属性属于类，也不能被序列化

    public Person(){}

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, String nation) {
        this.name = name;
        this.age = age;
        this.nation = nation;
    }

    public Person(String name, Integer age, Student student, String nation) {
        this.name = name;
        this.age = age;
        this.student = student;
        this.nation = nation;
    }

    public Person(String name, int age, Student student) {
        this.name = name;
        this.age = age;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", student=" + student +
                ", nation='" + nation + '\'' +
                '}';
    }
}
