package util.testinterface.testClass;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/1/14 16:52
 * @File : TestMain
 * @Software: IntelliJ IDEA 2019.3.15
 */

import util.testinterface.MyInterface;

/**
 *
 * @Author maozp3
 * @Description: 测试接口的使用，同一个接口有两个实现类，该怎么用？
 * @Date: 2020/1/14 16:52
 */
public class TestMain {
    /**
     * 同一个接口有两个实现类，该怎么用？
     * 答：声明变量的时候，变量类型是接口（myInterface）。  但是传给该变量的对象（new **） 却是可以选择的，
     *      new不同的实现类，那么这个接口变量就是那个实现类了。（一个接口变量可以接受所有实现了这个接口的对象。）
     */
   static MyInterface myInterface = new OtherClass();   // new OtherClass()实现类
//    static MyInterface myInterface = new MyClass();  // new MyClass()实现类

    public static void main(String[] args) {
        myInterface.say();
    }
}
