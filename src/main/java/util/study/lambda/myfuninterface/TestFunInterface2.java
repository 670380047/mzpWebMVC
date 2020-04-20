package util.study.lambda.myfuninterface;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/22 0:15
 * @File : TestFunInterface2
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/22 0:15
 */
public class TestFunInterface2 {
    public static long getResult(long t1,long t2,FunInterface2<Long,Long> action){
        return action.myAction(t1,t2);
    }

    public static void main(String[] args) {


        long result = getResult(20,30,(t1,t2)->t1*t2);
        System.out.println("lambda的方式(相乘):"+result);
        long result3 = getResult(20,30,(t1,t2)->t1+t2);
        System.out.println("lambda的方式(相加):"+result3);


        long result1 = getResult(20, 30, new FunInterface2<Long, Long>() {
            @Override
            public Long myAction(Long t1, Long t2) {
                return t1*t2;
            }
        });
        System.out.println("匿名内部类的方式(相乘):"+result1);
    }
}
