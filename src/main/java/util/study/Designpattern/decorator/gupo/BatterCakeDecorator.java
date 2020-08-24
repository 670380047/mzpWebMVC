package util.study.Designpattern.decorator.gupo;

/**
 * 【装饰器模式】
 *      扩展套餐的抽象装饰器 BatterCakeDecorator ,继承抽象类BatterCake
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/24 14:54
 * @File : BatterCakeDecorator
 * @Software: IntelliJ IDEA 2019.2.04
 */
public abstract class BatterCakeDecorator extends BatterCake{
    // 静态代理，委派
    private  BatterCake batterCake;

    // 一个有参构造方法，参数类型是传入最上层抽象类BatterCake
    public BatterCakeDecorator (BatterCake batterCake){
        this.batterCake = batterCake;
    }

    // 其他功能
    protected abstract void doSomething();

    @Override
    protected String getMsg() {
        return this.batterCake.getMsg();
    }

    @Override
    protected int getPrice() {
        return this.batterCake.getPrice();
    }
}
