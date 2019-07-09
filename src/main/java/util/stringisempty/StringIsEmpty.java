package util.stringisempty;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/22 16:05
 * @File : StringIsEmpty
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.util.StringUtils;

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
        Long myLong = 1L;
        System.out.println(StringUtils.isEmpty(myLong));
    }

    //重写StringUtils中的 isEmpty方法
    public static boolean isEmpty( Object str) {
        return str == null || "".equals(str)||"null".equals(str)||"NULL".equals(str);
    }
}
