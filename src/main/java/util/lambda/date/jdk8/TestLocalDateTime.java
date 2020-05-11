package util.lambda.date.jdk8;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/9 14:47
 * @File : TestLocalDateTime
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * 1. jdk8的日期表示： localDate   localTime   localDateTime
 * 2. Instant：时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）
 * 3. Duration: 计算两个时间之间的间隔
 *    period: 计算两个日期时间的间隔
 * 4. TemporalAdjuster : 时间校正器
 * 5. DateTimeFormatter：格式化时间/日期
 * 6. ZoneDate、ZonedTime、ZonedDateTime:时区格式化
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/9 14:47
 */
public class TestLocalDateTime {
    /**
     * 本地系统时间测试： localDate   localTime   localDateTime
     */
    @Test
    public void test1(){
        System.out.println("===================获取当前系统时间=================");
        // 获取当前系统时间。三个用法一样，都是调用now()方法
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);    // 当前系统时间： 2020-05-09T14:54:15.094
        System.out.println(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));  // 格式化后：2020-05-09 14:54:15

        System.out.println("===================获取指定时间=================");
        LocalDateTime ldt2 = LocalDateTime.of(2020,5,9,14,57);
        System.out.println(ldt2);

        System.out.println("===================时间运算,加两年=================");
        LocalDateTime plusYear =  ldt.plusYears(2);
        System.out.println(plusYear);

        System.out.println("===================时间运算,减两月=================");
        LocalDateTime minusYear =  ldt.minusMonths(2);
        System.out.println(minusYear);
        System.out.println("===================获取时间的信息,减两月=================");
        System.out.println(ldt.getYear()+"年");
        System.out.println(ldt.getMonthValue()+"月");
        System.out.println(ldt.getDayOfMonth()+"日");
        System.out.println(ldt.getHour()+"时");
        System.out.println(ldt.getMinute()+"分");
        System.out.println(ldt.getSecond()+"秒");
    }

    /**
     * Instant：时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）
     */
    @Test
    public void test2(){
        /**
         * 默认获取UTC的时间（协调世界时，又称世界标准时间，简称UTC）
         * 北京时间比UTC时间早8个小时，写作 UTC+8
         */
        Instant ins1 = Instant.now();   // 这里的now()获取的是UTC时间
        System.out.println("UTC时间："+ins1);  // 2020-05-09T07:24:22.567Z

        OffsetDateTime odt =  ins1.atOffset(ZoneOffset.ofHours(8));
        System.out.println("UTC时间+8小时："+odt);   // 2020-05-09T15:24:22.567+08:00

        System.out.println("时间戳："+ins1.toEpochMilli()); // toEpochMilli()是转化为毫秒
    }

    /**
     *  Duration: 计算两个时间之间的间隔
     *  period: 计算两个日期时间的间隔
     */
    @Test
    public void test3(){
        System.out.println("===========================计算“时间”的间隔===================");
        Instant inst1 = Instant.now();
        try {
            Thread.sleep(1000); //  线程等待1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant inst2 = Instant.now();
        Duration duration =  Duration.between(inst1,inst2);
        System.out.println("Instant类测试:"+duration.toMillis());


        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(1500); //  线程等待1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime lt2 = LocalTime.now();
        Duration duration2 = Duration.between(lt1,lt2);
        System.out.println("LocalTime类测试："+duration2.toMillis());

        System.out.println("===========================计算“日期”的间隔===================");
        LocalDate ld1 = LocalDate.of(2017,3,25);
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1,ld2);
        System.out.println(period.getYears()+"年");
        System.out.println(period.getMonths()+"月");
        System.out.println(period.getDays()+"日");
        System.out.println("一共"+period.toTotalMonths()+"个月");
    }

    /**
     * TemporalAdjuster : 时间校正器
     * TemporalAdjusters类是TemporalAdjuster接口的实现类
     */
    @Test
    public void test4(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(22); // 精确指定到一个月某一天。这里是指定到了20号
        System.out.println(ldt2);
        /**
         * TemporalAdjusters 类提供的好多种方法
         */
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.firstDayOfNextMonth()); // 获取下月号
        System.out.println(ldt3);

        /**
         * 自定义：计算下个工作日
         */
         LocalDateTime ldt5 =  ldt.with((e)->
            {
                LocalDateTime ldt4 = (LocalDateTime) e;
                DayOfWeek dow = ldt4.getDayOfWeek();
                if(dow.equals(DayOfWeek.FRIDAY)){
                    return ldt4.plusDays(3);
                }else if(dow.equals(DayOfWeek.SATURDAY)){
                    return ldt4.plusDays(2);
                }else {
                    return ldt4.plusDays(1);
                }
            });
        System.out.println("下个工作日是："+ldt5.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * DateTimeFormatter：格式化时间/日期
     */
    @Test
    public void test5(){
        /**
         * 自定义格式
         */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        /**
         * 利用jdk自带的格式
         */
        DateTimeFormatter dtf2 = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("没有格式化的时间："+localDateTime);

        /**
         * 从两个角度出发，一个是从格式，一个是从时间
         * 格式化方法一：从格式出发来调用format
         * 格式化可以直接使用：DateTimeFormatter格式的format(要被格式化的时间)方法
         */
        String dateTimeStr = dtf.format(localDateTime);
        System.out.println("格式化后的时间:"+dateTimeStr);

        /**
         * 格式化方法二：从时间出发来调用format
         * 格式化的时候也可以使用localDateTime的format(DateTimeFormatter的格式)
         */
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        /**
         * 反解析。使用localDateTime 的parse(时间，格式)
         */
        LocalDateTime newData = localDateTime.parse(dateTimeStr,dtf);
        System.out.println("格式化后，反解析回来："+newData);
    }

    /**
     * ZoneDate、ZonedTime、ZonedDateTime:时区格式化
     */
    @Test
    public void test6(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        System.out.println("支持的时区：");
        set.forEach(e-> System.out.println(e));
    }
}
