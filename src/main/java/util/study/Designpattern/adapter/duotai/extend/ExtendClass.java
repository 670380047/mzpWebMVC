package util.study.Designpattern.adapter.duotai.extend;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/21 17:08
 * @File : ExtendClass
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 继承了一个接口，但是没有实现接口的方法。 有另一个类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/21 17:08
 */
public class ExtendClass {
    public void say(){
        System.out.println("我是ExtendClass类的say()方法，并不是TestInterface接口的实现方法");
    }
}
