package util.study.reflect;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/28 16:33
 * @File : Person
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 反射机制测试的实体类
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/28 16:33
 */
@MyAnnotation("mzp的注解(类上面)")
public class Person extends Creature<String> implements MyInterface,Comparable {
    public String name;
    private Integer age;
    public final int testFinal = 123;


    public Person(){}

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation("mzp的注解(方法上面)")
    public void eat(){
        System.out.println("person吃饭(public方法)");
    }

    public void sleep() throws Exception,RuntimeException {

        System.out.println("person睡觉(private方法)");
        throw new Exception("mzp自定义异常,测试反射");
    }

    private String wangToFly(){
        System.out.println("Person想要飞翔");
        return "飞翔吧";
    }


    public String getName() {
        return name;
    }

    public void setName(String name,double score,int age) {
        System.out.println("setName方法运行了");
        this.name = name;
        System.out.println(name);
        System.out.println(score);
        System.out.println(age);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public void breathe() {
        System.out.println("person呼吸（重写接口的方法）");
    }

    /**
     * 重写Comparable接口的方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }

    /**
     * 内部类
     */
    public class Computer{}
    private class Mobile{}

}
