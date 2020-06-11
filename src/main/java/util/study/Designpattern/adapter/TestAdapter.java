package util.study.Designpattern.adapter;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/3 12:50
 * @File : TestAdapter
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 适配器模式。测试类
 *
 * 适配器模式。将两种（多种）完全不同的事物联系到一起。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/3 12:50
 */
public class TestAdapter {
    public static void main(String[] args) {
        //创建手机类
        Phone iPhone = new Phone("iphoneX");
        // 创建适配器类
        VoltageAdapter voltageAdapter = new VoltageAdapter();
        // 给手机添加一种适配器属性
        iPhone.setAdapter(voltageAdapter);
        // 开启手机的充电功能。内部使用给定的适配器
        iPhone.charge();
    }
}
