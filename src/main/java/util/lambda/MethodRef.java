package util.lambda;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/22 13:22
 * @File : MethodRef
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用：若lambda方法体中的内容有方法已经实现了，我们可以用“方法引用”
 *          （可以理解为方法引用是Lambda表达式的另一种表现形式）
 *
 *  主要有三种语法格式：
 *      1.  对象::实例方法名
 *      2.  类::静态方法名
 *      3.  类::实例方法名（需要特殊规则：两个参数中，第一个参数是该实例方法的调用者，第二个参数是该实例方法的参数）
 *
 *  方法引用注意：
 *  lambda 体中调用方法的参数列表和返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型一致
 *
 *
 *  二、构造器引用
 *     语法格式：
 *          类::new
 *
 *  用法注意事项：根据函数式接口中抽象方法的参数列表，会自动对应 类中的 某种类型（无参、一个参数、两个参数...）的构造器
 *
 *
 *  三、数组引用
 *     语法格式：
 *          类型::new
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/22 13:22
 */
public class MethodRef {

    /**
     * 类::静态方法名
     */
    @Test
    public void test1(){
        Consumer<String> consumer = (x)-> System.out.println(x);
        consumer.accept("lambda打印");

        //System.out其实是一个打印流PrintStream。其中println是打印流的一个对象方法
        PrintStream PS = System.out;
        /**
         * 用 PS::println  方法引用去代替了lambda表达式
         *
         * 这里有个条件：方法引用中的方法(println)，他的参数列表和返回值
         *  要和
         *  被他实现的函数式接口（Consumer接口）的中的抽象方法（accept方法）的  参数列表和返回值 类型相同
         */
        Consumer<String> consumer1 = PS::println;
        consumer1.accept("方法引用 打印");
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test2(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        int result1 = com.compare(2,3);
        System.out.println("lambda表达式比较2和3大小的结果："+result1);

        /**
         * //参数列表和返回值类型要与函数式接口中抽象方法的参数列表和返回值类型相同。
         */
        Comparator<Integer> com2 = Integer::compare;   //方法引用的括号都不用写，因为参数列表相同，所以参数列表都可以推断出来
        int result2 = com2.compare(2,3);
        System.out.println("静态方法引用比较2和3的大小："+result2);
    }

    /**
     * 类::实例方法名
     *      需要特殊规则：两个参数中，第一个参数是该实例方法的调用者，第二个参数是该实例方法的参数
     */
    @Test
    public void test3(){
        /**
         * BiPredicate<T, U> 函数式接口
         *      boolean test(T t, U u)  抽象方法
         */
        BiPredicate<String,String> biPredicate = (x,y) -> x.equals(y);
        boolean bo1 = biPredicate.test("m","z");
        System.out.println("lambda表达式比较两个字符串m和z是否相同："+bo1);

        /**
         * 需要特殊规则：两个参数中，第一个参数是该实例方法的调用者，第二个参数是该实例方法的参数
         */
        BiPredicate<String,String> biPredicate1 = String::equals;
        boolean bo2 = biPredicate1.test("m","z");
        System.out.println("类::实例方法。比较两个字符串m和z是否相同："+bo2);
    }

    /**
     * 构造器引用。
     * 类::new
     *     用法注意事项：根据函数式接口中抽象方法的参数列表，会自动对应 类中的 某种类型（无参、一个参数、两个参数...）的构造器
     */
    @Test
    public void test4(){
        /**
         * lambda表达式方式：创建一个实例化对象的动作
         */
        Supplier<UserInfo> supplier = ()-> new UserInfo();
        /**
         * 此时通过函数式接口中的 供给型函数，就可以获取一个对象实例
         */
        UserInfo userInfo = supplier.get();
        System.out.println("lambda表达式调用构造器："+userInfo);

        /**
         * 现在：使用构造器引用
         *      根据函数式接口中抽象方法的参数列表，会自动对应 类中的 某种类型的构造器
         */
        Supplier<UserInfo> supplier1 = UserInfo::new;   //Supplier 接口的抽象方法是无参的，所以这里对应的是无参构造方法
        UserInfo userInfo1 = supplier1.get();
        System.out.println("供给型接口（Supplier）调用无参构造器："+userInfo1);

        Function<Integer,UserInfo> function = UserInfo::new;
        UserInfo userInfo2 = function.apply(25);
        System.out.println("函数型接口（Function）调用一个参数的构造器："+userInfo2);

        BiFunction<String,String,UserInfo> biFunction = UserInfo::new;
        UserInfo userInfo3 = biFunction.apply("mzp","123");
        System.out.println("函数星接口的子接口（BiFunction）调用两个参数的构造器："+userInfo3);
    }

    /**
     * 数组引用
     */
    @Test
    public void test5(){
        // 需求：返回一个指定长度的String[] 类型的数组
        Function<Integer,String[]> function = (x)-> new String[x];
        String[] strings = function.apply(10);
        System.out.println("使用lambda表达式的数组引用，创建一个指定长度的String[]数组："+strings.length);

        Function<Integer,String[]> function1 = String[]::new;
        String[] strings1 = function1.apply(15);
        System.out.println("使用“数组引用”创建了一个指定长度的String[]数组:"+strings1.length);
    }
}
