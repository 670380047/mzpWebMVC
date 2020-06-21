package util.study.Designpattern.adapter.duotai.extend;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/21 17:10
 * @File : TestMain
 * @Software: IntelliJ IDEA 2019.3.15
 */

import util.study.Designpattern.adapter.duotai.TestInterface;

/**
 * 我实现了TestInterface接口，但是没有实现他的方法。 他的方法刚好在我的另一个父类ExtendClass里面有一个相同签名的方法，被我继承过来了
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/21 17:10
 */
public class TestClass extends ExtendClass implements TestInterface {
    // 这里有一个say()方法是继承自ExtendClass父类的。 刚好替我实现了TestInterface接口中的say()方法。
    // 而且ExtendClass父类和TestInterface接口并不存在父子关系。 只是有个方法一样而已。
}
