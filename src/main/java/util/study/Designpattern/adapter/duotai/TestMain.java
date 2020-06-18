package util.study.Designpattern.adapter.duotai;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/18 18:12
 * @File : TestMain
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/18 18:12
 */
public class TestMain {
    public static void main(String[] args) {
        /**
         * 1.这里实现的是第四层（最底层）的实例，然后传给了第二层的抽放类的变量。
         * 那么这个变量能够调用（抽象类自己有的抽象方法，只是被子类实现了）的方法都是来自于这个第四层实例的、被第四层给实现了（或者继承第三层实现的）的方法。
         */
       TestAbstract testClass2 = new TestClass2();
        /**
         * 2.这个方法是第三层实现了一下，但是第四层又重写了一下。
         * 因为这里是底色层的实例，所以调用的是第四层最后重写的（第三层的覆盖了）
         */
       testClass2.eat();
        /**
         * 3.这个方法第四层没有再重写，那么他就是继承的第三层实现后的方法（直接把第三层的给继承了过来）
         */
       testClass2.say();
//       testClass2.wangFly();
    }
}
