package util.study.Designpattern.direction;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:48
 * @File : Bread
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 装饰者模式： 面包类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:48
 */
public class Bread extends Food{
    private Food foodName;

    public Bread() {
    }

    public Bread(Food foodName) {
        this.foodName = foodName;
    }

    @Override
    public String make() {
        String str = this.foodName.make()+"+面包";
        return str;

    }
}
