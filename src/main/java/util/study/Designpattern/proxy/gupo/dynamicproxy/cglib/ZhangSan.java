package util.study.Designpattern.proxy.gupo.dynamicproxy.cglib;

/**
 * 【代理模式】
 *      cglib动态代理代理实现类：张三
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:09
 * @File : ZhangSan
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ZhangSan {

    private String require = "肤白、貌美、大长腿";


    public void findLove() {
        System.out.println("儿子（张三）的要求："+require);
    }


    public void buyCar() {
        System.out.println("张三买了辆保时捷");
    }
}
