package util.listtest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/15 14:17
 * @File : DateFormat
 * @Software: IntelliJ IDEA 2019.3.15
 */
public class DateFormat {

    public static void main(String[] args) {

//        test();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = "2019-1-30 16:10:30";
        try {
            String resultTime = getFirstDayOfNextMonth(df.parse(dateTime));
            System.out.println("下个月1号："+resultTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
    * @Author maozp3
    * @Description: 获取任意给定时间的下个月一号
    * @Date: 17:26 2019/5/13
    * @Param []
    * @return void
    **/
    public static  String getFirstDayOfNextMonth(Date formatedDateTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();

             //把指定时间放进去，作为接下来时间处理的基准
            calendar.setTime(formatedDateTime);
            System.out.println("模板时间是："+formatedDateTime);
            //设置day为第一天。
            calendar.set(Calendar.DAY_OF_MONTH  ,1);
            System.out.println("月："+calendar.get(Calendar.MONTH));  //传入时间是8月份，这里显示的结果是7（因为从0开始）
            System.out.println("本月1号:"+df.format(calendar.getTime())); //尽量先把日期改为本月1号，然后再把月份改为下个月
            //设置月为当前月的下一个月份
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1); //月份从0（0-11）开始计算。所以传入的是7，但显示的是8
            //24小时制的小时，设为0
            calendar.set(Calendar.HOUR_OF_DAY  ,0);
            calendar.set(Calendar.MINUTE  ,0);
            calendar.set(Calendar.SECOND  ,0);
            String resultTime = df.format(calendar.getTime());
            return resultTime;
    }

    /**
     * @Author maozp3
     * @Description: 为指定时间延期。延期N个月。 返回一个yyyy-MM-dd HH:mm:ss 格式的Date类型
     * （在月份上增加延期数（这里是add，而不是set.可以避免1月31号加一个月后直接变成3月的问题））
     * @Date: 9:59 2019/6/25
     * @Param [formatedDateTime, delayMonth] formatedDateTime为指定的时间。  delayMonth为需要延期的月份数。
     * @return java.lang.String
     **/
    public  Date getdelayDateOfCurrentMonth(Date formatedDateTime,Long delayMonth){
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


    public static void test(){
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =new Date();
        Date date1 = new Date();
        String dateTime = "2019-03-22 16:10:30";
        int month =date.getMonth()+1; //（月份需要加1）
        System.out.println("date时间月份："+month+"月");
        System.out.println("d格式化时间："+d.format(date));
        System.out.println("df格式化时间："+df.format(date));
        try {
            date1 = df.parse(dateTime);
            System.out.println("字符串格式化为date类型（parse）："+date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("==========华丽的分割线===============");
        //Calendar（日期）
        Calendar calendar =Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        System.out.println("Calendar年:"+year+"年");
        //（月份需要加1）
        int cMonth =calendar.get(Calendar.MONTH)+1;
        System.out.println("Calendar月份："+cMonth+"月");

        int day = calendar.get(Calendar.DATE);
        System.out.println("Claendar日期："+day+"日");
        String cDateStr =year+"年"+cMonth+"月"+day+"日";
        System.out.println("Calendar日期："+cDateStr);

        //HOUR_OF_DAY   24小时制。    HOUR是12小时制。
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("Calendar小时："+hour);
        int minute = calendar.get(Calendar.MINUTE);
        System.out.println("Calendar分："+minute);
        int second = calendar.get(Calendar.SECOND);
        System.out.println("Calendar秒:"+second);
        String cTimeStr =hour+"时"+minute+"分"+second+"秒";
        System.out.println("Calendar时间："+cTimeStr);
    }

}
