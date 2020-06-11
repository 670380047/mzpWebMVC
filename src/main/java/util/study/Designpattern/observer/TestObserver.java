package util.study.Designpattern.observer;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:22
 * @File : Test
 * @Software: IntelliJ IDEA 2019.3.15
 */

/** 观察者模式
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:22
 */
public class TestObserver {
    public static void main(String[] args) {
        Boss boss = new Boss("老板");
        Staff1 staff1 = new Staff1("员工1");
        Staff1 staff2 = new Staff1("员工2");
        boss.addStaff(staff1);
        boss.addStaff(staff2);
        boss.sendMessage("明天早上10:00，到大会议室开会。");
    }
}
