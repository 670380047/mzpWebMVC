package util.demo;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/27 18:01
 * @File : DemoTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/7/27 18:01
 */
public class DemoTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Map<String,Integer> map = new HashMap<>();
        map.put("a",a);
        map.put("b",b);
        StringBuffer str = new StringBuffer();
        str.append(123);
        System.out.println("交换前：a="+a+",b="+b+",str="+str);
        test(a,b,str);
        System.out.println("交换后：a="+a+",b="+b+",str="+str);
        System.out.println("=======================================================");
        System.out.println("交换前：a="+map.get("a")+",b="+map.get("b"));
        testMap(map);
        System.out.println("交换后：a="+map.get("a")+",b="+map.get("b"));

        System.out.println("=======================================================");

        Bean first = new Bean(1);
        Bean second = new Bean(2);
        System.out.println("交换前：a="+first.getA()+",b="+second.getA()+",str="+str);
        first.swap(second);
        System.out.println("交换后：a="+first.getA()+",b="+second.getA()+",str="+str);

    }

    public static void test(Integer a, Integer b,StringBuffer str){
        str = str.append(456);
        Integer tem;
        tem = a;
        a = b;
        b = tem;
        System.out.println("交换中：a="+a+",b="+b+",str="+str);
    }

    public static void testMap(Map<String,Integer> map){
        Integer tem;
        tem = map.get("a");
        map.put("a",map.get("b"));
        map.put("b",tem);
    }


}
