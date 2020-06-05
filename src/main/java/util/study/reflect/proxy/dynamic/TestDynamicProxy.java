package util.study.reflect.proxy.dynamic;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/5 17:08
 * @File : TestDynamicProxy
 * @Software: IntelliJ IDEA 2019.3.15
 */

import util.study.reflect.proxy.Boss;
import util.study.reflect.proxy.CoalSecretary;
import util.study.reflect.proxy.SteelSecretary;

/**
 * 动态代理：测试类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/5 17:08
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler();
        Boss boss =  dynamicProxyHandler.newProxyInstance(new CoalSecretary());
        boss.meet();
        boss.consume();
        boss.eat("冒菜");
        System.out.println("*****************************************************");
        Boss boss1 =  dynamicProxyHandler.newProxyInstance(new SteelSecretary());
        boss1.meet();
        boss1.consume();
        boss1.eat("炸酱面");
    }
}
