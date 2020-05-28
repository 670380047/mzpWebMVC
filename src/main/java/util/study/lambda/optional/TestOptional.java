package util.study.lambda.optional;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/8 15:02
 * @File : TestOptional
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;
import util.study.lambda.UserInfo;

import java.util.Optional;

/**
 * 测试Optional容器类
 *
 * Optional容器类的常用方法:
 * Optional.of(T t) :创建一个Optional实例
 * Optional.empty() :创建一个空的 Optional实例
 * Optional.ofNullable(T t):若t不为nu1l,创建Optional实例,否则创建空实例
 * isPresent() :判断是否包含值
 * orElse(T t) :如果调用对象包含值， 返回该值，否则返回t
 * orElseGet(Supplier s) :如果调用对象包含值， 返回该值,否则返回s获取的值
 * map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
 * flatMap(Function mapper):与map类似,要求返回值必须是0ptional
 *
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/8 15:02
 */
public class TestOptional {

    /**
     * 测试Optional类
     */
    @Test
    public void test0(){
        Optional<Man> op = Optional.ofNullable(new Man());    // 江疏影
//        Optional<Man> op = Optional.ofNullable(null);       // 江莱
        String name = getGoddessName(op);
        System.out.println(name);
    }

    public String getGoddessName(Optional<Man> man){
        return man.orElse(new Man("小明",Optional.ofNullable(new Goddess("江莱"))))        //如果man不为空，就直接返回man的属性。如果man为空，就返回参数里面新实例化的new Man
                .getGoddess()       // 获取这个man的女神信息。得到的还是个Optional包装过的Goddess：Optional<Goddess>，所以他还可以是用Optional的方法
                .orElse(new Goddess("江疏影")) // 如果调用者Optional<Goddess>本身不为空，就是用她自己的属性，否则就是用括号里的“江疏影”
                .getName();
    }


    /**
     * Optional.of(T t) :创建一个Optional实例
     */
    @Test
    public void test1(){
        Optional<UserInfo> op = Optional.of(new UserInfo());
        UserInfo userInfo = op.get();
        System.out.println(userInfo);
    }

    /**
     * Optional.empty() :创建一个空的 Optional实例
     */
    @Test
    public void test2(){
        Optional<UserInfo> op = Optional.empty();       // op是一个空的
        UserInfo userInfo = op.get();       // 此处会报空元素异常：java.util.NoSuchElementException: No value present
        System.out.println(userInfo);
    }

    /**
     * Optional.ofNullable(T t):若t不为nu1l,创建Optional实例,否则创建空实例
     * isPresent() :判断是否包含值
     * orElse(T t) :如果调用对象包含值， 返回该值，否则返回t
     * orElseGet(Supplier s) :如果调用对象包含值， 返回该值,否则返回s获取的值
     */
    @Test
    public void test3(){
        /**
         * Optional.ofNullable(T t):若t不为nu1l,创建Optional实例,否则创建空实例
         */
        Optional<UserInfo> op = Optional.ofNullable(new UserInfo());
        /**
         * // isPresent() :判断是否包含值
         */
        if(op.isPresent()){
            UserInfo userInfo = op.get();
            System.out.println(userInfo);
        }


        UserInfo userInfo1 = null;
        Optional<UserInfo> op1 = Optional.ofNullable(null);     // op1是一个空的
        if(op1.isPresent()){       //isPresent() :判断是否包含值
            userInfo1 = op1.get();     // 此处会报空元素异常：java.util.NoSuchElementException: No value present
        }else{
            /**
             * orElse(T t) :如果调用对象包含值， 返回该值，否则返回参数t
             */
            userInfo1 = op1.orElse(new UserInfo("mzp","123",25));  // orElse(T t) :如果调用对象包含值， 返回该值，否则返回t
        }
        System.out.println(userInfo1);


        UserInfo userInfo2 = null;
        Optional<UserInfo> op2 = Optional.ofNullable(null);
        /**
         * orElseGet(Supplier s) :如果调用对象包含值， 返回该值,否则返回s获取的值
         */
        userInfo2 = op2.orElseGet(()-> new UserInfo());
        System.out.println(userInfo2);
    }

    /**
     * map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
     */
    @Test
    public void test4(){
        Optional<UserInfo> op = Optional.ofNullable(new UserInfo("mzp","123",25));
        /**
         * map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
         */
        Optional<String> userName = op.map(e->e.getUsername());
        System.out.println("map:"+userName);

        Optional<UserInfo> op1 = Optional.ofNullable(new UserInfo());
        /**
         * map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
         */
        Optional<String> userName1 = op1.map(e->e.getUsername());
        System.out.println("map:"+userName1);


        /**
         * flatMap(Function mapper):与map类似,要求返回值必须是0ptional
         */
        Optional<String> userName2 = op.flatMap(e->Optional.ofNullable(e.getUsername()));   // 返回值也要用Optional包装一下
        System.out.println("flatMap:"+userName2);
    }
}
