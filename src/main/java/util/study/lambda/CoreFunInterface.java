package util.study.lambda;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/22 12:29
 * @File : CoreFunInterface
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置的四大核心函数式接口
 * 一：
 *  Consumer<T>: 消费型接口
 *     void accept(T t);
 *
 * 二：
 *  Supplier<T>: 供给型接口
 *      T get();
 *
 * 三：
 *  Function<T,F>: 函数型接口
 *      R apply(T t);
 *
 * 四：
 *  predicate<T>: 断言型接口
 *      boolean test(T t);
 *
 *
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/22 12:29
 */
public class CoreFunInterface {

    /**
     * Consumer<T> 消费型接口
     *      void accept(T t);
     */
    @Test
    public void test1(){
        happy(10000,(m)-> System.out.println("买手机消费了"+m+"元"));
    }

    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

    /**
     * supplier<T> 供给型接口
     *      T get();
     */
    @Test
    public void test2(){
        List<Integer> numList =  getNumList(10,()-> (int)(Math.random()*100));
        numList.stream().forEach((n)-> System.out.println(n));
    }
    /**
     * 需求：产生指定个数的整数，并放入集合中。调用供给型接口Supplier 来实现
    * @Description:
    * @Author maozp3
    * @Date: 12:58 2020/4/22
    * @Param [num, supplier]
    * @return java.util.List<java.lang.Integer>
    **/
    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<num;i++){
            Integer result = supplier.get();    //调用函数式接口的方法
            list.add(result);
        }
        return list;
    }

    /**
     *  Function<T,F>: 函数型接口
     *       R apply(T t);
     */
    @Test
    public void test3(){
        String str = "     用java8内置的函数型接口来处理，去除前后空格 \t\t\t\t";
        System.out.println(str);
        String strResult = strHandler(str,(s)-> s.trim());
        System.out.println(strResult);

        String strSub = strHandler(str,(sub)->sub.substring(2,sub.length()-10));
        System.out.println(strSub);
    }

    /**
     * 用于处理字符串的模板方法。调用java8内置的 函数型接口Function 来处理
    * @Description:
    * @Author maozp3
    * @Date: 13:00 2020/4/22
    * @Param [str, function]
    * @return java.lang.String
    **/
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }

    /**
     * predicate<T>: 断言型接口
     *       boolean test(T t);
     */
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello","maoZongPeng","lambda","test","mzp","ok","not ok");
        List<String> listResult = filterStr(list,(s)->s.length()>3);  //过滤字符串长度大于3的字符串
        listResult.stream().forEach((str)-> System.out.println(str));
    }

    /**
     * 对字符串进行过滤操作。调用java8内置的断言型接口 Predicate 来处理
    * @Description:
    * @Author maozp3
    * @Date: 13:11 2020/4/22
    * @Param [list, predicate]
    * @return java.util.List<java.lang.String>
    **/
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> strList = new ArrayList<>();
        for(String str : list){
            if(predicate.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

}
