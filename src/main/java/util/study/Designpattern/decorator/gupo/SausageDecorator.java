package util.study.Designpattern.decorator.gupo;

/**
 *【装饰器模式】
 *      香肠装饰器类。继承装饰器类BatterCakeDecorator
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/24 15:19
 * @File : SausageDecorator
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class SausageDecorator extends BatterCakeDecorator{
    // 调用了装饰器抽象基类BatterCakeDecorator的构造方法，给属性赋值。
    public SausageDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected void doSomething() {
        System.out.println("香肠装饰器。做一些其他的事情...");
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+1根香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 2;
    }
}
