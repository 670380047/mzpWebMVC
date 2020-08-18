package util.study.Designpattern.proxy.gupo.dynamicproxy.jdk;

/**
 * 【代理模式】
 *      jdk动态代理。人的接口：规范
 *      jdk动态代理是通过实现被代理类的接口，然后实现接口中的方法。
 *      这样当被代理类有什么新的方法时，被代理类也可以自动拥有。
 *      最重要的区别是，动态代理不是硬编码，当我们换一个业务场景，即换一个接口的时候，仍然可以使用。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:07
 * @File : IPerson
 * @Software: IntelliJ IDEA 2019.2.04
 */
public interface IPerson {
    // 寻找真爱
    void findLove();

    //买车
    void buyCar();
}
