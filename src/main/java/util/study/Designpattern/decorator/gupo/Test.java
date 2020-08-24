package util.study.Designpattern.decorator.gupo;

/**
 * 【装饰者模式】
 *      测试类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/24 15:23
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    public static void main(String[] args) {
        BatterCake batterCake;
        //路边买了一个煎饼
        batterCake = new BaseBatterCake();
        // 再给煎饼加一个鸡蛋
        batterCake = new EggDecorator(batterCake);
        // 一个鸡蛋不够，再加一个鸡蛋
        batterCake = new EggDecorator(batterCake);
        // 再加一根香肠吧
        batterCake = new SausageDecorator(batterCake);

        // 看看这个煎饼想在长啥样了，再看看看多少钱
        System.out.println(batterCake.getMsg()+",总价:"+batterCake.getPrice());

        //附加一些其他的事吧
        ((SausageDecorator)batterCake).doSomething();
    }
}
