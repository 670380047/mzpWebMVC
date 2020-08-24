package util.study.Designpattern.decorator;

/**
 * 装饰者模式： 面包类。（在这里就是“被包装类”）
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/24 15:35
 * @File : Bread
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Bread implements Food{
    //被包装类的基础属性
    private String foodName;

    public Bread(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String make() {
        return this.foodName;
    }

    //目前并未做实现
    @Override
    public Food addFood() {
        return null;
    }
}
