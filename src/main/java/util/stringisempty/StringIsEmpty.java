package util.stringisempty;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/22 16:05
 * @File : StringIsEmpty
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.io.UnsupportedEncodingException;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/5/22 16:05
 */
public class StringIsEmpty {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        String myLong = "null";
        System.out.println(StringIsEmpty.isEmpty(myLong));

        try {
            //样例：把“猫”这个字符串通过UTF-8解析为字符数组之后，在通过utf-8来编码成新的字符串。
            String mzp = new String("猫".getBytes("UTF-8"),"utf-8");
            System.out.println("猫："+mzp);
            //目前的理解：
            //用法：当一个字符串过来的时候，发现是乱码（可能是因为解码方式不对，比如传过来的是utf-8，但我们却用gbk解析了的原因）。
            //这个时候我们可以用utf-8先把传过来的字符串解析成字符数组。再用utf-8把字符串还原成为字符串就好了。
            byte[] isoString = "毛".getBytes("utf-8");
            System.out.println("utf-8解码后："+isoString);
            String utf8String = new String(isoString,"utf-8");
            System.out.println("utf-8编码后:"+utf8String);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //重写StringUtils中的 isEmpty方法
    public static boolean isEmpty( Object str) {
//        return str == null || "".equals(str)||"null".equals(str)||"NULL".equals(str);
        return str == null || "".equals(str)||"NULL".equalsIgnoreCase(str.toString());

    }
}
