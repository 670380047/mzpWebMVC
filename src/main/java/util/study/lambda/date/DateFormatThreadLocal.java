package util.study.lambda.date;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/9 13:02
 * @File : DateFormatThreadLocal
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用threadLoacl锁定线程变量。保证线程安全
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/9 13:02
 */
public class DateFormatThreadLocal {
    /**
     * 声明一个DateFormat类型的ThreadLocal变量，并且调用了ThreadLocal内部的初始化方法 protected T initialValue()方法
     */
    private static final ThreadLocal<DateFormat> sdf = new ThreadLocal<DateFormat>(){
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 定义了一个日期转换方法，供外部使用
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date dateConvert(String source) throws ParseException {
        return sdf.get().parse(source);
    }
}
