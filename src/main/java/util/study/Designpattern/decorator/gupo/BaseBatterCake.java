package util.study.Designpattern.decorator.gupo;

/**
 * 【装饰器模式】
 *      被装饰类
 *      一个基本的煎饼. 继承抽象类BatterCake
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/24 14:51
 * @File : BaseBatterCake
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class BaseBatterCake extends BatterCake{
    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
