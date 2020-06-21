package util.study.Designpattern.adapter.duotai;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/18 18:12
 * @File : TestMain
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 一、多态：
 *  向上转型：父类变量可以调用“父类中被子类重写的方法”，即：用父类变量调用的方法，该方法的具体内容是在子类中被实现的那个具体内容。
 *  向下转型（需要强转）：子类变量可以调用“子类中特有的那些方法”。
 *      向下转型的前提是：已经发生过向上转型。  然后才存在向下转型（强转）。不
 *      不存在直接的向下转型即：直接将“父类实例传给子类变量”，编译错误。 反例：TestClass2 testClass4 = new TestClass();
 * 二、访问修饰符：
 *      父类中四种访问修饰符的变量都可以被继承。但是只有private类型的变量不能直接访问，需要通过访问器。
 *      （推荐，所有访问类型都要用访问器去访问，不要直接去访问。规范一点。）
 * 三、抽象类。
 *      抽象类不能直接实例化。
 *       但是你在“实例化”的过程中，要求你实现他的抽象方法（如果是个空的抽象类，他会要求你实现一个父类的方法）
 *     注意：这个所谓的“实例化”，  这就相当于你这个其实就是new了个子类，实现了这个抽象类的抽象方法
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/18 18:12
 */
public class TestMain {
    public static void main(String[] args) {
        /**
         * 一、多态：
         * 1.这里实现的是第四层（最底层）的实例，然后传给了第二层的抽放类的变量。
         * 那么这个变量能够调用（抽象类自己有的抽象方法，只是被子类实现了）的方法都是来自于这个第四层实例的、被第四层给实现了（或者继承第三层实现的）的方法。
         */
       TestAbstract testClass2 = new TestClass2();
        /**
         * 2.这个方法是第三层实现了一下，但是第四层又重写了一下。
         * 因为这里是第四层的实例，所以调用的是第四层最后重写的（第三层的覆盖了）
         */
       testClass2.eat();
        /**
         * 3.这个方法第四层没有再重写，那么他就是继承的第三层实现后的方法（直接把第三层的给继承了过来）
         */
       testClass2.say();
        /**
         * 4.向下转型（强转）。
         * 向下转型的前提是：已经发生过向上转型。  然后才存在向下转型（强转）。
         *      不存在直接的向下转型即：直接将“父类实例传给子类变量”，编译错误。 反例：TestClass2 testClass4 = new TestClass();
         */
        System.out.println("这里发生了向下转型：*********");
        TestClass2 testClass21 = (TestClass2)testClass2;
       testClass21.wangFly();





        System.out.println("--------------测试访问修饰符的继承关系---------------");
        /**
         * 二、访问修饰符：
         */
        TestClass2 testClass = new TestClass2();
        testClass.setName("张三");
        testClass.setAge(18);
        testClass.setSex("男");
        testClass.setScore(150);
        System.out.println("直接访问父类的public变量："+testClass.name);
        System.out.println("通过构造器访问父类的public变量："+testClass.name);
        System.out.println("直接访问父类的protected变量（因为存在继承关系）："+testClass.getScore());
        System.out.println("通过构造器访问父类的protected变量："+testClass.getScore());
        System.out.println("直接访问父类的默认变量(因为在同一个包下)："+testClass.sex);
        System.out.println("通过构造器访问父类的默认变量："+testClass.getSex());
        System.out.println("直接访问父类的private变量："+"不允许（编译出错）");
        System.out.println("通过构造器访问父类的private变量："+testClass.getAge());

        /**
         * 5.this关键字指向当前对象。可以用来当做返回值“表示把当前对象返回出去。可以用对应的类型变量去接受”
         */
        System.out.println("this关键字指向当前对象,并且作为返回值:");
        TestClass testClass22 = testClass.mySelf();
        System.out.println("返回了this之后，两个对象是否相等：testClass22==testClass---》"+(testClass22==testClass));
        System.out.println(testClass22);
        /**
         * 静态方法也可以继承.但不存在重写的情况。只存在隐藏(如果你在子类中又定义了一个相同签名的静态方法，那么父类的静态方法将被隐藏，而不是重写)
         */
        System.out.println("静态方法也可以继承------------------------");
        TestClass3.myStatic();

        System.out.println("-------------抽象类-------------");
        /**
         * 三、抽象类。
         * 抽象类不能直接实例化。
         *  但是你在“实例化”的过程中，要求你实现他的抽象方法（如果是个空的抽象类，他会要求你实现一个父类的方法）
         *      这就相当于你这个“实例化”其实就是new了个子类，实现了这个抽象类的抽象方法
         */
        TestClass3 testClass3 = new TestClass3() {
            /**
             * “实例化”过程中，实现抽象类自己的抽象方法。
             */
            @Override
            public void hello() {
                System.out.println("实现了抽象类TestClass3的抽象方法hello");
            }

            /**
             * 实现父类的方法。（如果是空的抽象类就必须要选择一个父类的方法来实现或重写）
             */
            @Override
            void wangFly() {
                super.wangFly();
                super.say();
                super.eat();
                System.out.println("我是抽象类TestClass3下“实例化”过程中，被要求重写父类的任意方法（至少要重写一个方法）");
            }
        };
        System.out.println("******start*****");
        testClass3.wangFly();  //实例化过程中“被抽象类重写的方法”
        System.out.println("*******end****");
        testClass3.eat();   //继承自父类的方法
        testClass3.say();   //继承自父类的方法
        testClass3.walk(); // 抽象类自己的普通方法
        testClass3.hello(); // 抽象类的抽象方法
    }
}
