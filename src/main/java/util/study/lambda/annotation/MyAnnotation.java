package util.study.lambda.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/11 9:44
 * @File : MyAnnotation
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
*  自定义的可重复注解，拥有一个属性 value()。   value这个属性的名字是固定的，就是value
 *  1. 可重复注解需要用 @Repeatable(容器类.class)  来声明一下
* @Author maozp3
* @Date: 9:51 2020/5/11
* @Param
* @return
**/
@Repeatable(MyAnnotations.class)    // 声明一下是可重复注解，参数是容器类
// 作用于：类、属性、方法、参数、构造器、局部变量
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "mzp的自定义“可重复注解”";
}
