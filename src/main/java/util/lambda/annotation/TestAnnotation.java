package util.lambda.annotation;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/11 9:44
 * @File : TestAnnotation
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

/**
 * jdk8  重复注解
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/11 9:44
 */
public class TestAnnotation {
    @Test
    public void test1(){
        Class<TestAnnotation> clazz = TestAnnotation.class;
        try {
            // 获取该类的指定方法
            Method methodShow = clazz.getMethod("show");
            // 获取该方法上的注解，参数是注解类，返回是这个类型的数组（因为是可重复注解）。
            MyAnnotation[] myAnnotations =  methodShow.getAnnotationsByType(MyAnnotation.class);
            for (MyAnnotation myAnnotation : myAnnotations){
                System.out.println(myAnnotation.value());
            }

            System.out.println("====================测试 RequestMapping 注解==================");
            /**
             * 测试 RequestMapping 注解
             */
            RequestMapping testAnnotation =  methodShow.getAnnotation(RequestMapping.class);
            String[] value = testAnnotation.value();// value()方法返回的是字符串数组
            for(String val : value){
                System.out.println(val);
            }
            RequestMethod[] requestMethods =  testAnnotation.method();  // method()方法返回的是字符串数组
            for(RequestMethod requestMethod : requestMethods){
                System.out.println(requestMethod);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @MyAnnotation("hello")
    @MyAnnotation("test")
    @RequestMapping(value = "测试自定义注解",method = RequestMethod.POST)
    public void show(){

    }
}
