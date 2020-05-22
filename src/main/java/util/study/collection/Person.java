package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/20 14:40
 * @File : Persion
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.Objects;

/**
 * 集合测试的实体类
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/20 14:40
 */
public class Person {
    private String name;
    private int age;

    public Person(){}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    // 系统自动创建的 equals方法。 判断逻辑太简单，不能保证内容相同就是同一个对象
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }

    // 自己重写了equals方法。可以保证内容相同，就是同一个对象
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof  Person){
            Person p = (Person) obj;
            if(this.getName().equals(p.getName()) && this.getAge() == p.getAge()){
                return true;
            }
        }
        return false;
    }

    // 系统重写的 hashCode方法。 可以保证相同内容生成的hashCode值一样。
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
