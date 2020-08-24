package util.study.Designpattern.decorator.gupo;

/**
 * 【装饰器模式】
 *      煎饼抽象类.这里的作用跟接口一样，只提供了规范
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/24 14:49
 * @File : BatterCake
 * @Software: IntelliJ IDEA 2019.2.04
 */
public abstract class BatterCake {
    //获取煎饼里面的东西
    protected  abstract  String getMsg();
    //获取煎饼的价格
    protected  abstract int getPrice();
}
