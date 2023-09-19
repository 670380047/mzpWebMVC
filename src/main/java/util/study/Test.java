package util.study;

import java.util.Random;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2022/4/19 10:05
 * @File : Test
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Test {
    int value;

    public Test (int value) {
        this.value = value;
    }

    /**
     * 对象没有变化。
     *      因为入参传递的指向两个对象的两个变量a和b， 然后方法里面将两个变量a和b指向的对象地址互换了一下，
     *      相当与变成了a指向了b原来的对象地址，b指向了a原来的对象地址。  但是！！ 对象地址里面的内容并没有发生变化。
     * @param a
     * @param b
     */
    private static void swapInstance(Test a, Test b) {
        Test temp = a;
        a = b;
        b = temp;
    }

    /**
     * 对象里面的值被交换了。
     *      因为入参传递的指向两个对象的两个变量a和b，然后在方法里面把两个对象里面保存的值给交换了，
     *      即，两个对象地址里面存的东西被交换了。  （对象还是那俩对象，但是对象里面保存的东西被交换了）
     * @param a
     * @param b
     */
    private static void swapInstanceValue(Test a, Test b) {
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
    }


    /**
     * 包装类型比较大小
     */
    private static void objectCompare() {

        Long aLong = 128l;
        Long bLong = 128l;

        System.out.println(aLong == bLong); // false  (对象比较，Long缓存了 [-128,127]的对象)
        System.out.println(aLong.longValue() == bLong.longValue()); // true。 拆箱，将对象变成了基本类型。

//        Integer a = 0;
    }

    /**
     * 三目运算
     */
    private static void ternaryOperation() {

        boolean flag = true;
        Integer a  = 1;
        int b = 2;
        int k = flag ? a : b;
        System.out.println("三目运算符：" + k);
    }


    public static void main(String[] args) {
        Test test1 = new Test(1);
        Test test2 = new Test(2);
        System.out.println("test1:" + test1.value);// test1:1
        System.out.println("test2:" + test2.value);// test2:2
        swapInstance(test1, test2);
        System.out.println("test1:" + test1.value);// test1:1
        System.out.println("test2:" + test2.value);// test2:2

        swapInstanceValue(test1, test2);
        System.out.println("test1:" + test1.value);// test2:2
        System.out.println("test2:" + test2.value);// test1:1

        // 包装类型比较大小
        objectCompare();

        // 三目运算符
        ternaryOperation();
    }
}
