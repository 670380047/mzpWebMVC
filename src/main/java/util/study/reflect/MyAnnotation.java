package util.study.reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 注解类. Person上用到的注解
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/3 11:27
 * @File : MyAnnotation
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME) //  注解的声明周期：SOURCE 注解存在于源代码中，编译时会被抛弃。    CLASS编译时注解。   RUNTIME:运行时注解
public @interface MyAnnotation {

    String value() default "mzp测试反射的自定义注解";
}
