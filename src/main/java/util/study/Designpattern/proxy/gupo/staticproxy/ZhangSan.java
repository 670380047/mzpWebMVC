package util.study.Designpattern.proxy.gupo.staticproxy;

/**
 * 【代理模式】
 *      静态代理实现类：张三
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/15 14:09
 * @File : ZhangSan
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class ZhangSan implements IPerson{

    private String require = "肤白、貌美、大长腿";

    @Override
    public void findLove() {
        System.out.println("儿子（张三）的要求："+require);
    }
}
