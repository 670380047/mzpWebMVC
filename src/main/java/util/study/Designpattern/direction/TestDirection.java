package util.study.Designpattern.direction;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:52
 * @File : TestDirection
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 装饰者模式：测试类
 *      每一层都可以添加一些自己的逻辑（即：装饰）进去。
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:52
 */
public class TestDirection {
    public static void main(String[] args) {
        /**
         * 1.这个对象从最里层的new Food开始，  依次实例化到 最外层的new Bread
         */
        Food food = new Bread(new Cream(new Vegetable(new Food("鸡肉"))));
        /**
         * 2.然后调用make方法的时候。
         * 从最外层的new Bread实例开始调用。
         *      然后Bread类中的make()方法又调用了他自己内部的Food实例(就是这里的new Cream实例)的make()方法
         *      然后Cream类中的make()方法又调用了他自己内部的Food实例(就是这里的new Vegetable实例)的make()方法。
         *      然后Vegetable类中的make()方法又调用了他自己内部的Food实例(就是这里的new Food实例)的make()方法。
         *      最后Food类中的make()方法，返回了一个字符传。
         *
         *      然后开始逆向一次返回，类似于地递归操作，一层一层的往外面返回。
         */
        System.out.println(food.make());
    }
}
