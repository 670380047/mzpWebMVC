package util.study.Designpattern.decorator;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:51
 * @File : Vegetable
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 装饰者模式： 蔬菜类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:51
 */
public class Vegetable extends Food{
    private Food foodName;

    public Vegetable() {
    }

    public Vegetable(Food foodName) {
        this.foodName = foodName;
    }

    @Override
    public String make() {
        String str = this.foodName.make()+"+蔬菜";
        return str;
    }
}
