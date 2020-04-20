package util.study.lambda.functionInterface;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/25 19:59
 * @File : TestFunctionInterface
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/10/25 19:59
 */
public class TestFunctionInterface {
    public static void main(String[] args) {
        TestFunctionInterface tfi = new TestFunctionInterface();
        //当lambda表达式只有一句的时候，表达式不需要加大括号，也不需要分号结尾
        tfi.myTest(()-> 2>1? true:false);
        //当lambda表达式大于一句的话，表达式就要加大括号，并且每句之间还要用分号隔开
        tfi.myTest(()-> {System.out.println("这是一个函数式接口！！！");return 1;});
        System.out.println("======================分割线==========================");
        FunInterface funInterface1 = ()-> {
            System.out.println("我的lambda");
            return 1;
        };
        // 上一行给funInterface这个函数式接口的对象赋了一个表达式，也就是给他的抽象方法提供了一个实现。（通过lambda表达式提供）
        // 然后这个funInterface对象就可以直接调用他的抽象方法了，此时这个对象实例的抽象方法已经被实现了，可以用了。
        funInterface1.myTestFun();

        //当lambda表达式只有一条语句，大括号和return都可以省略不写
        FunInterface funInterface2 = ()-> 2;
        tfi.myTest(funInterface2);
        System.out.println("这个对象是："+funInterface2);
        System.out.println("该对象的父类:"+funInterface2.getClass().getSuperclass());
        System.out.println("该对象实现了几个接口："+funInterface2.getClass().getInterfaces().length);
        System.out.println("该对象实现的接口是：");
        for(int i = 0;i<funInterface2.getClass().getInterfaces().length;i++){
            System.out.println(funInterface2.getClass().getInterfaces()[i]);
        }
    }

    public void myTest(FunInterface funInterface){
        System.out.println("函数式接口测试===============开始");
        System.out.println("打印一下结果："+(funInterface.myTestFun()));
        System.out.println("函数式接口测试===============结束");
        funInterface.toString();
    }

}
