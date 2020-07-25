package util.math;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/11/30 20:39
 * @File : MathTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/11/30 20:39
 */
public class MathTest {
    public static void main(String[] args) {
        long result=0;
        for(int j=1;j<=5;j++){
            result += fun(j);
            System.out.println(j+"的阶乘和："+result);
        }

        System.out.println("最终结果："+result);

//        long sum=0;
//        int n=1;
//        for( ;n<=5;n++){
//            sum=test(n);
//        }
//
//        System.out.println("1-"+--n+"的阶乘求和结果："+sum);
    }

    /**
     * @Author maozp3
     * @Description: 计算某个数的阶乘.
     * @Date: 21:13 2019/11/30
     * @Param [a]
     * @return long
     **/
    public static long fun(long a){
        if(a==1){
            return 1;
        }else{
            return a*fun(a-1);
        }
    }

    /**
    * @Author maozp3 
    * @Description: 计算n的阶乘和。  比如:1！+2！+3！+4！+....+n!
    * @Date: 21:15 2019/11/30
    * @Param [n]
    * @return long
    **/
    public static long  test(int n){
        long result=0;
        long tem=1;
        for(int i=1;i<=n;i++){
            tem = tem *i;  // tem就是前一个数的阶乘。 比如求 i!。  那么此时tem就是  (i-1)!
            result += tem;
        }
        System.out.println(n+"的阶乘和"+result);
        return result;
    }


    @Test
    public void test1(){
        /**
         * 在包装类型中
         * -128 -- 127  这个范围内的整数被包装在固定的对象中。所以相等
         */
        Integer a = 127;
        Integer b = 127;
        System.out.println(a==b);  // true

        /**
         * 在包装类型中
         * 不在-128 -- 127 范围内的数，即使数值相等，但也是两个不同的对象。因此用 == 比较是不相等的。
         *      如果想要比较不在上面的范围内包装类型的值的话，用equals
         */
        Integer c = 1000;
        Integer d = 1000;
        System.out.println(c==d); // false
        System.out.println(c.equals(d)); // true

        /**
         * 在基本类型中。数值相等，即为相等
         */
        int a1 = 1000;
        int b1 = 1000;
        System.out.println(a1==b1);  // true

    }

}
