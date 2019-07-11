package util.generictest;
/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/9 11:40
 * @File : GenericClassTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.List;

/**
 *
 * @Author maozp3
 * @Description:  定义一个泛型类
 * @Date: 2019/7/9 11:40
 */
public class GenericClassTest<T> {

        /**
         * 这个不是泛型方法，只是使用了泛型类中已声明的T
         */
        public void test1(T t, List list){
            System.out.println(t.toString());
        }
        /** 生命泛型方法：是在方法的返回值前面加上<T> （字母可以任意写，可是A、B、C....等）
         * 泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
         * 由于下面的泛型方法在声明的时候声明了泛型<E>，因此即使在泛型类中并未声明泛型，
         * 编译器也能够正确识别泛型方法中识别的泛型。
         */
        public <E> void test2(E e){
            System.out.println(e.toString());
        }
        /**
         * 在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型;
         * 可以与泛型类中声明的T不是同一种类型。
         */
        public <T> void test3(T t){
            System.out.println(t.toString());
        }



}
