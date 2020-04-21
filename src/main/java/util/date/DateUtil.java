package util.date;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/20 12:27
 * @File : DateUtil
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/20 12:27
 */
public class DateUtil {
    /**
     * @Author maozp3
     * @Description: 获取指定日期 + days天
     * @Date: 15:50 2019/12/2
     * @Param [formatedDateTime, days]
     * @return java.lang.String
     **/
    public  String getCurrentDayAddDay(Date formatedDateTime, int days){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String resultDateTime="";
        try {

            //以指定的时间为基准。
            calendar.setTime(formatedDateTime);
            //设置day为指定日期 + days天后。   比如 2019-12-2 + 2天后，  就是2019-12-4
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+days);
            //将月份设置为当前日期的下一个月
            //calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
            //24小时制的小时，设为0
//            calendar.set(Calendar.HOUR_OF_DAY  ,calendar.get(Calendar.HOUR_OF_DAY));   //formatedDateTime.getHours() 已经被替换
//            calendar.set(Calendar.MINUTE  ,calendar.get(Calendar.MINUTE));  //formatedDateTime.getMinutes()
//            calendar.set(Calendar.SECOND  ,calendar.get(Calendar.SECOND));   //formatedDateTime.getSeconds()
            //将设置好的时间转换为字符串类型
            resultDateTime = df.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return resultDateTime;
    }


    /**
     * @Author maozp3
     * @Description: 获取给定时间的下个月一号。 返回一个yyyy-MM-dd 格式的字符串
     * @Date: 18:58 2019/5/13
     * @Param [Date] 时间类型
     * @return java.lang.String
     **/
    public  String getFirstDayOfNextMonth(Date formatedDateTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String resultDateTime="";
        try {
            //以指定的时间为基准。
            calendar.setTime(formatedDateTime);
            //设置day为第一天
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            //将月份设置为当前日期的下一个月
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
            //24小时制的小时，设为0
            calendar.set(Calendar.HOUR_OF_DAY  ,0);
            calendar.set(Calendar.MINUTE  ,0);
            calendar.set(Calendar.SECOND  ,0);
            //将设置好的时间转换为字符串类型
            resultDateTime = df1.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultDateTime;
    }

    /**
     * @Author maozp3
     * @Description: 获取给定时间的本月一号。 返回一个yyyy-MM-dd 格式的字符串
     * @Date: 18:58 2019/5/13
     * @Param [Date] 时间类型
     * @return java.lang.String
     **/
    public  String getFirstDayOfCurrentMonth(Date formatedDateTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String resultDateTime="";
        try {
            //以指定的时间为基准。
            calendar.setTime(formatedDateTime);
            //设置day为第一天
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            //24小时制的小时，设为0
            calendar.set(Calendar.HOUR_OF_DAY  ,0);
            calendar.set(Calendar.MINUTE  ,0);
            calendar.set(Calendar.SECOND  ,0);
            //将设置好的时间转换为字符串类型
            resultDateTime = df1.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("获取指定时间的下个月1号出错。");
//            logger.error(e.getMessage()!=null&&e.getMessage().length()>=512?e.getMessage().substring(0,512):e.getMessage());
        }
        return resultDateTime;
    }

    /**
     * @Author maozp3
     * @Description: 获取给定时间的本月最后一天。 返回一个yyyy-MM-dd 格式的字符串
     * @Date: 18:58 2019/5/13
     * @Param [Date] 时间类型
     * @return java.lang.String
     **/
    public  String getLastDayOfCurrentMonth(Date formatedDateTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        String resultDateTime="";
        try {
            //以指定的时间为基准。
            calendar.setTime(formatedDateTime);

            //先把day设置为第一天
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            //将月份设置为当前日期的下一个月
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);

            //把day设置为前一天（下个月1号的前一天，就是本月最后一天）
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);

            //24小时制的小时，设为0
            calendar.set(Calendar.HOUR_OF_DAY  ,0);
            calendar.set(Calendar.MINUTE  ,0);
            calendar.set(Calendar.SECOND  ,0);
            //将设置好的时间转换为字符串类型
            resultDateTime = df1.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("获取指定时间的下个月1号出错。");
//            logger.error(e.getMessage()!=null&&e.getMessage().length()>=512?e.getMessage().substring(0,512):e.getMessage());
        }
        return resultDateTime;
    }


    /**
     * @Author maozp3
     * @Description: 为指定时间延期。延期N个月。 返回一个yyyy-MM-dd HH:mm:ss 格式的Date类型
     * （在月份上增加延期数（这里是add，而不是set.可以避免1月31号加一个月后直接变成3月的问题））
     * @Date: 9:59 2019/6/25
     * @Param [formatedDateTime, delayMonth] formatedDateTime为指定的时间。  delayMonth为需要延期的月份数。
     * @return java.lang.String
     **/
    public  Date getDelayDateOfCurrentMonth(Date formatedDateTime,Long delayMonth){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        //做一个类型转换。把Long转换成int类型
        Integer delayMonthCount = delayMonth.intValue();
        String resultDateTime="";
        try {
            //以指定的时间为基准。
            calendar.setTime(formatedDateTime);
            //在月份上增加延期数（这里是add，而不是set.可以避免1月31号加一个月后直接变成3月的问题）
            calendar.add(Calendar.MONTH,delayMonthCount);
//            calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+delayMonthCount);
            //24小时制的小时，设为0
            calendar.set(Calendar.HOUR_OF_DAY  ,0);
            calendar.set(Calendar.MINUTE  ,0);
            calendar.set(Calendar.SECOND  ,0);
            //将设置好的时间转换为字符串类型
            resultDateTime = df.format(calendar.getTime());
            date = df.parse(resultDateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

        DateUtil crmUserService = new DateUtil();
        String date = "2016-1-31 01:30:06";
        String str = crmUserService.getCurrentDayAddDay(df.parse(date),2);
        System.out.println("str="+str);


//
//        System.out.println("直接测试："+d.format("null"));
//
//
//        String stringTime = "2019-11-12 20:42:34.0";
//        Date resultDate = df.parse(stringTime);
//        String resultString = d.format(resultDate);
//        System.out.println("yyyyMMddHHmmss测试时间格式："+resultString);
//
//        Date resultDate2 = df.parse(stringTime);
//        String resultString2 = d.format(resultDate2);
//        System.out.println("yyyy-MM-dd HH:mm:ss测试时间格式："+resultString2);


//        try {
//            String str ="123";
//            System.out.println(str.substring(0,1024));
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("测试"+e.getMessage().length());
//            logger.error(e.getMessage()!=null&&e.getMessage().length()>=512?e.getMessage().substring(0,512):e.getMessage());
//        }
//        CrmUserService crmUserService = new CrmUserService();
//        crmUserService.test();
    }
}
