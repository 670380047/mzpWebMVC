package util.study.Designpattern.decorator;/**
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
         * 1.这个对象从“最里层”的new Chicken开始，
         */
        Food food = new Bread("面包");
        // 用“蔬菜类”包装一下
        food = new Vegetable(food);
        // 用“奶油类”包装一下
        food = new Cream(food);
        // 用鸡肉类包装一下
        food  = new Chicken(food);
//        Food food = new Bread(new Cream(new Vegetable(new Chicken("鸡肉"))));
        /**
         * 2.然后调用make方法的时候。
         * 从“最外层”的new Chicken实例开始调用。Chicken内部是Cream类的实例
         *      然后Cream类中的make()方法又调用了他自己内部的Food实例(就是这里的new Vegetable实例)的make()方法。
         *      然后Vegetable类中的make()方法又调用了他自己内部的Food实例(就是这里的new Bread实例)的make()方法。
         *      最后Bread类中的make()方法，返回了一个字符传。
         *
         *      然后开始逆向依次次返回到Chicken中，类似于地递归操作，一层一层的往外面返回。
         */
        System.out.println(food.make());

    }
}
