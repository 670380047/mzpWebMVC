package util.study.lambda.annotation;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/11 9:51
 * @File : MyAnnotations
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 可重复注解的容器。
*  还需要一个注解的容器。作用于可重复注解上
* @Author maozp3
* @Date: 9:51 2020/5/11
* @Param 
* @return 
**/
// 作用于：类、属性、方法、参数、构造器、局部变量
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotations {
    MyAnnotation[] value();
}
