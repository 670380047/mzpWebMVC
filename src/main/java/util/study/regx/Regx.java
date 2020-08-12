package util.study.regx;

import java.util.regex.Pattern;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/8/1 0:22
 * @File : Regx
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Regx {
    public static void main(String[] args) {
        String str = "\\'";
        System.out.println("'");//  '
        System.out.println("\'");//  '
        System.out.println("\\'");//  \'  因为第二个斜杠被转义了，注意（在字符串中）被转义了就变成正常的字符了，不参与之后的转义。
        System.out.println("'".matches("'"));   //true
        System.out.println(".".matches("\'"));   //true
        System.out.println("'".matches("\\'"));	//true
        System.out.println("'".matches("\\\'"));	//true
        System.out.println("'".matches("\\\\'"));	//false     （后面这个是 \' ）
        System.out.println("\'".matches("\\'"));  //true
        System.out.println("\'".matches("\'"));  //true
        System.out.println("\\'".matches("\\\'")); //false
        System.out.println("\\'".matches("\\\\'")); //true      （后面这个是 \' ）

        System.out.println("==================================");
        System.out.println("\\.".matches("."));//false  注意（在字符串中）被转义了就变成正常的字符了，不参与之后的转义。
        System.out.println(".".matches("\\.")); //true   注意（正则中）被转义了就变成正常的字符了，依旧会参与之后的转义。
        System.out.println("\\\\".matches("\\\\")); // false     \\ 不等于 \
        System.out.println("\\\\.");    // 结果是  \\.   注意（在字符串中）被转义了就变成正常的字符了，不参与之后的转义。
        System.out.println(Pattern.compile("\'").matcher("\'").matches());  // true

    }
}
