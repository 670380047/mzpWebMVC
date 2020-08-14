package util.study.Designpattern.prototype;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * 【原型模式】。测试模板
 *      被克隆的对象需要实现一个“标记接口”：Cloneable   表示这个类实现了克隆
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/12 18:24
 * @File : Person
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Person implements Cloneable,Serializable{
    String name;
    int age;
    Integer score;  // 基本包装类型会被深拷贝
    // 引用类型。（浅拷贝无法复制，还是同一个引用。  深拷贝可以指向一个新的引用）
    List<String> hobbies;


    public Person() {
    }

    public Person(String name, int age, Integer score, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.hobbies = hobbies;
    }

    /**
     * 继承Object的clone方法。 并且把修饰符改为public
     *      1.protected类型对“包内成员可见、对子类可见”；
     *      2.若子类和基类不在同一包中，那么在子类中，子类实例可以访问其中基类“继承”过来的protected方法，
     *          但是不能访问基类实例的protected方法。
     *
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    /**
     *  自定义的深拷贝方法。 利用序列化和反序列化 重建对象的特性，来完成深拷贝
     * @return
     * @throws CloneNotSupportedException
     */
    public Person deepClone() throws CloneNotSupportedException {
        Person personClone = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            //开始序列化....
            // 创建一个数组节点输出流。来保存我们的对象（和序列化相同，只不过我们不往文件中写，而是就放在内存的一个数组中来保存）
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 创建一个对象输出流，来序列化一个对象到一个节点流中
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            // 把当前对象this，序列化到一个数组中
            objectOutputStream.writeObject(this);

            //开始反序列化...
            // 从刚才输出的内存中，来取出我们的对象数据
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            // 用对象输入流包装一下
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            // 读取对象输入流。即，反序列化创建一个新的对象。
            personClone = (Person) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            // 关闭输出流。内部会关闭节点流
            if(objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭输入流。内部会关闭节点流
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return personClone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", hobbies=" + hobbies +
                '}';
    }

    // =====下面全是get/set。不用看了====


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getScore(), person.getScore()) &&
                Objects.equals(getHobbies(), person.getHobbies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getScore(), getHobbies());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
