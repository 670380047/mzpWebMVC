package com.example.aop;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/10/12 17:06
 * @File : AopTestController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/10/12 17:06
 */
@Controller
public class AopTestController {

    @RequestMapping("aopTest")
    @ResponseBody
    public String aopTest(){
        System.out.println("aop的@pointcut测试");
        return "aop的@pointcut测试";
    }



}
