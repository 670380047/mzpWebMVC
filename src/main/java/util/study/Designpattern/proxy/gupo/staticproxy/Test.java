package util.study.Designpattern.proxy.gupo.staticproxy;

/**
 * 【代理模式】
 *          静态代理：测试类
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:14
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    public static void main(String[] args) {
        IPerson zhangSanParent = new ZhangSanParent(new ZhangSan());
        zhangSanParent.findLove();
    }
}
