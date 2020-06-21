package util.study.Designpattern.adapter.duotai.extend;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/21 17:12
 * @File : TestMain
 * @Software: IntelliJ IDEA 2019.3.15
 */

import util.study.Designpattern.adapter.duotai.TestInterface;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/21 17:12
 */
public class TestMain {
    public static void main(String[] args) {
        TestInterface testInterface = new TestClass();
        testInterface.say();
    }
}
