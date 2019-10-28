package util.lambda.functionInterface;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/25 19:47
 * @File : FunInterface
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *  自定义一个函数式接口：只有一个抽象方法的接口，就是函数式接口。
 *  其中 @FunctionalInterface 注解可有可无；有的话，方便检查：比如不满足函数式接口时，就会提示报错
 * @author maozp3
 * @description:
 * @date: 2019/10/25 19:47
 */
@FunctionalInterface
public interface FunInterface {
    /***
    * description: 函数式接口中唯一的一个抽象方法。
    * @author maozp3
    * @date: 19:50 2019/10/25
    * @param: []
    * @return void
    */
    Object myTestFun();

    //这个是覆盖java.lang.Object类中的public方法，所以这个不算做是函数式接口中的抽象方法。
    String toString();
}
