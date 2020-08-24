package util.study.Designpattern.decorator.gupo;

/**
 * 【装饰器模式】
 *      鸡蛋装饰器类。 继承装饰器类BatterCakeDecorator
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/24 15:14
 * @File : EggDecorator
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class EggDecorator extends BatterCakeDecorator{
    // 调用了装饰器抽象基类BatterCakeDecorator的构造方法，给属性赋值。
    public EggDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    // 方法的增强功能
    @Override
    protected void doSomething() {
        System.out.println("鸡蛋装饰器。做一些其他的事情...");
    }

    @Override
    protected String getMsg() {
        return super.getMsg()+"+1个鸡蛋";
    }

    @Override
    protected int getPrice() {
        return super.getPrice()+1;
    }
}
