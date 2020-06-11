package util.study.Designpattern.direction;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:50
 * @File : Cream
 * @Software: IntelliJ IDEA 2019.3.15
 */

/** 装饰者模式： 奶油类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:50
 */
public class Cream extends Food{
    private Food foodName;

    public Cream() {
    }

    public Cream(Food foodName) {
        this.foodName = foodName;
    }

    @Override
    public String make() {
        String str = this.foodName.make()+"+奶油";
        return str;
    }
}
