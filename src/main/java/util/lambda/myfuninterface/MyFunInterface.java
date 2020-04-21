package util.lambda.myfuninterface;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/21 22:38
 * @File : Myfuninterface
 * @Software: IntelliJ IDEA 2019.3.15
 */
@FunctionalInterface
public interface  MyFunInterface<T> {
     T  getValue(T value);
}
