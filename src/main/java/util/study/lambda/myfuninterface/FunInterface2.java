package util.study.lambda.myfuninterface;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/22 0:14
 * @File : FunInterface2
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 声明了带两个参数的泛型类。期中我一个参数用来做入参，一个用来做返回值。我随意使用
* @Description:
* @Author maozp3
* @Date: 0:18 2020/4/22
* @Param
* @return
**/
public interface FunInterface2<T,R> {
    R myAction(T t1,T t2);
}
