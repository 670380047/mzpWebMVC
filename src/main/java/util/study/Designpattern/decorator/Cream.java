package util.study.Designpattern.decorator;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:50
 * @File : Cream
 * @Software: IntelliJ IDEA 2019.3.15
 */

/** 装饰者模式： 奶油类（装饰类）
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:50
 */
public class Cream implements Food{
    /**
     * 把被包装的原对象保存起来。
     * 在类外部操作的是被包装后的新对象，但是在这个类内部操作做的还是原对象。
     *
     */
    private Food food;

    private Cream() {
    }

    /**
     * 创建一个有参的构造方法。参数传的就是接口
     * @param foodName
     */
    public Cream(Food foodName) {
        this.food = foodName;
    }

    @Override
    public String make() {
        String str = this.food.make()+"+奶油";
        return str;
    }

    @Override
    public Food addFood() {
        return null;
    }
}
