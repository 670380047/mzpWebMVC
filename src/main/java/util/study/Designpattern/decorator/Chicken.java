package util.study.Designpattern.decorator;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:48
 * @File : Bread
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 装饰者模式： 鸡肉类（装饰类）
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:48
 */
public class Chicken implements Food{
    /**
     * 把被包装的原对象保存起来。
     * 在类外部操作的是被包装后的新对象，但是在这个类内部操作做的还是原对象。
     *
     */
    private Food food;

    private Chicken() {
    }

    /**
     * 创建一个有参的构造方法。参数传的就是接口
     * @param foodName
     */
    public Chicken(Food foodName) {
        this.food = foodName;
    }

    @Override
    public String make() {
        String str = this.food.make()+"+鸡肉";
        return str;

    }

    @Override
    public Food addFood() {
        return null;
    }
}
