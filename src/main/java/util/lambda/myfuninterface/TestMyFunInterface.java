package util.lambda.myfuninterface;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/21 22:40
 * @File : TestMyFunInterface
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/21 22:40
 */
public class TestMyFunInterface {

    // 需求：将给定的字符串转换为大写,并且去除首位空格。

    /** 方法说明：1  2  3
     * 1.这个方法的作用是给定一个字符串str。
     * 2.然后再给定一个函数式接口，函数式接口有一个方法getValue(T value)去处理这个str。至于怎么处理，则没有给出，需要去实现，看3 。
     * 3.可以通过匿名内部类，或者lambda表达式 去给出函数式接口中的抽象方法他的具体的处理动作。
     *
     * 个人总结：函数式接口是以泛型<T>的形式定义的，下面用的时候把这个T具体化成了String，那么此时如果
     * 函数式接口里面所有使用泛型类的<T>都会变成String类型。
     *      （如果存在自定义泛型方法，而且用了重名的T，这个T不是String。
     *      详情参照util.generictest.GenericClassTest这个类里面的二或三）
     * @param str
     * @param mft
     * @return
     */
    public static String strHandler(String str, MyFunInterface<String> mft){
        return mft.getValue(str);
    }

    public static void main(String[] args) {
        String testStr = "    毛宗鹏 的 mzp  \t\t";
        System.out.println(testStr);


        // 用匿名内部类的方式
        String resultStr2 = strHandler(testStr, new MyFunInterface<String>() {
            @Override
            public String getValue(String value) {
                return value.toUpperCase().trim();      //关键就是这一句话。
            }
        });
        System.out.println("匿名内部类的方式:"+resultStr2);

        // 用lambda表达式的方式
        String resultStr = strHandler(testStr,(str)->str.toUpperCase().trim());
        System.out.println("lambda的方式:"+resultStr);
    }
}
