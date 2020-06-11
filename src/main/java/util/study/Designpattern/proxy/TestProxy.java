package util.study.Designpattern.proxy;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/5 16:34
 * @File : TestProxy
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 代理模式
 *  静态代理： 测试类。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/5 16:34
 */
public class TestProxy {

    public static void main(String[] args) {
        /**
         * 煤老板的秘书
         */
        CoalSecretary coalSecretary = new CoalSecretary();
        coalSecretary.meet();
        coalSecretary.consume();

        /**
         * 钢铁老板的秘书
         */
        SteelSecretary steelSecretary = new SteelSecretary();
        steelSecretary.meet();
        steelSecretary.consume();
    }
}
