package util.testinterface.testClass;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/1/14 16:50
 * @File : MyClass
 * @Software: IntelliJ IDEA 2019.3.15
 */

import util.testinterface.MyInterface;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2020/1/14 16:50
 */
public class MyClass implements MyInterface {
    @Override
    public void say() {
        System.out.println("MyClass is say hello");
    }
}
