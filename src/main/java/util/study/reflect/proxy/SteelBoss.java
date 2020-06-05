package util.study.reflect.proxy;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/5 16:35
 * @File : SteelBoss
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 代理模式
 *  静态代理： 钢铁老板类。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/5 16:35
 */
public class SteelBoss implements Boss{
    @Override
    public void meet() {
        System.out.println("钢铁老板见面了");
    }

    @Override
    public void consume() {
        System.out.println("钢铁老板消费了");
    }

    @Override
    public void eat(String food) {
        System.out.println("钢铁老板吃"+food);
    }
}
