package util.lambda.optional;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/8 17:03
 * @File : Godness
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 女神类
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/8 17:03
 */
public class Goddess {
    private String name;
    private int age;

    public Goddess() {
    }

    public Goddess(String name) {
        this.name = name;
    }

    public Goddess(int age) {
        this.age = age;
    }

    public Goddess(String name, int age) {
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

    @Override
    public String toString() {
        return "Goddess{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
