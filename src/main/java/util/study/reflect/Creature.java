package util.study.reflect;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/3 11:22
 * @File : Creature
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 生物类。 person的父类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/3 11:22
 */
public class Creature<T> {
    public String color;
    int legs;

    public Creature() {
    }

    public Creature(String color, int legs) {
        this.color = color;
        this.legs = legs;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public class Head{}
}
