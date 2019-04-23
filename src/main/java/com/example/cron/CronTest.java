package com.example.cron;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/19 16:39
 * @File : CronTest
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/19 16:39
 */
@Service("cronService")
public class CronTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Scheduled(cron = "*/5 * * * * ?")
    public void helloCron(){
        System.out.println("定时器任务时间："+sdf.format(new Date()));
    }
}
