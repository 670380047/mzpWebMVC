package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/20 14:40
 * @File : Persion
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 集合测试的实体类
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/20 14:40
 */
public class Person implements Comparable{
    public String name;
    public Integer age;
    public int score;

    public Person(){}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public void eat(){
        System.out.println(this.getClass().getName()+":吃饭,我是Person类");
    }
    public void run(){
        System.out.println(this.getClass().getName()+":奔跑,我是Person类");
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    // 系统自动创建的 equals方法。 判断逻辑太简单，不能保证内容相同就是同一个对象
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }

    /**
     * 重写equals方法的时候，使用“equals and hashCode”选项，就会根据变量来生成（如下）。  如果直接选择“override Methods”的话，生成的就太简单了（如上）
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return score == person.score &&
                Objects.equals(name, person.name) &&
                Objects.equals(age, person.age);
    }


    // 自己重写了equals方法。可以保证内容相同，就是同一个对象
//    public boolean equals(Object obj){
//        if(this == obj){
//            return true;
//        }
//        if(obj instanceof  Person){
//            Person p = (Person) obj;
//            if(this.getName().equals(p.getName()) && this.getAge() == p.getAge() && this.getScore() == p.getScore()){
//                return true;
//            }
//        }
//        return false;
//    }

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
                ", score=" + score +
                '}';
    }

    /**
     * 重写了Comparable接口的 compareTo(Object o)方法，自定义排序规则。 大于返回1   等于返回0   小于返回-1
     * 为了能够使对象存入 TreeSet中。 因为TreeSet中的元素需要排序，利用的就是compareTo(Object o)方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NotNull Object o) {
        if(o instanceof  Person){
            Person p = (Person) o;
            if(this.getAge() == p.getAge()){
                return this.getName().compareTo( p.getName());    // String 自己有重写compareTo方法
            }else{
                return this.getAge().compareTo( p.getAge());  // Integer 自己也有重写compareTo方法
            }
        }
        return 0;
    }
}
