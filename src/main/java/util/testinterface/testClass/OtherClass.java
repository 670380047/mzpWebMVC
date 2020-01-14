package util.testinterface.testClass;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/1/14 16:51
 * @File : otherClass
 * @Software: IntelliJ IDEA 2019.3.15
 */

import util.testinterface.MyInterface;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2020/1/14 16:51
 */
public class OtherClass implements MyInterface {

    @Override
    public void say() {
        System.out.println("OtherClass is say hello");
    }
}
