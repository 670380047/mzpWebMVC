package util.math;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/6/22 17:11
 * @File : MathRound
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/6/22 17:11
 */
public class MathRound {
    public static void main(String[] args) {
        test();
        testCeil();
    }

    public static void test(){
        double a = 10;
        double b = 3;
        double result;
        result = Math.round(a/b);
        System.out.println(a+"/"+b+"(四舍五入的结果)="+result);

        result = Long.valueOf(Math.round(3.5333333));
        System.out.println(result);
    }

    /**
    * @Author maozp3
    * @Description: 向上取整、向下取整
    * @Date: 11:12 2019/6/25
    * @Param []
    * @return void
    **/
    public static void testCeil(){
        /**
         *Math.sqrt()//计算平方根
         *Math.cbrt()//计算立方根
         *Math.pow(a, b)//计算a的b次方
         *Math.max(a,b);//计算最大值
         *Math.min(a,b);//计算最小值
         */
        double a = 10;
        double b = 3;
        System.out.println(a+"/"+b+"正常结果="+a/b);
        System.out.println(a+"/"+b+"向上取整="+Math.ceil(a/b));
        System.out.println(a+"/"+b+"向下取整="+Math.floor(a/b));
    }
}
