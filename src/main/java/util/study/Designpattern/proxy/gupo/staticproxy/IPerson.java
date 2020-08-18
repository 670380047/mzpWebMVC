package util.study.Designpattern.proxy.gupo.staticproxy;

/**
 * 【代理模式】
 *      静态代理。人的接口：规范
 *      静态代理的缺点就是“硬编码”。没有办法做到“自适应”：无法做到想帮谁代理就帮谁代理。
 *      比如被代理对象现在要买车子，那么代理类就要修改了。添加一个买车的方法。 （动态代理就会自动实现这个方法）
 *      最要命的是硬编码，即只这个静态代理只有在这个场景下才有效，如果我换一个场景（即换一个接口），他就没法用了（因为接口中方法都变了）。
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
}
