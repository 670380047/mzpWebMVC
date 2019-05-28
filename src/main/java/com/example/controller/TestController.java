package com.example.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/18 11:20
 * @File : TestController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/5/18 11:20
 */
@Controller
public class TestController {

    @Autowired
    TestService testService;
    @RequestMapping("getAll")
    public String getUserInfoList(Model model){
        System.out.println("进入mybatis测试。。。");
        List<Map<String,Object>> userInfoList = testService.selectUserInfoAll();
        System.out.println("userInfo测试结果：");
        for(int i=0;i<userInfoList.size(); i++){
            System.out.println(userInfoList.get(i));
        }
        model.addAttribute("userInfoList", userInfoList);
        model.addAttribute("loginMessgae", "登陆成功");
        return "jsp/main";
    }

    @RequestMapping("getAllTest")
    public String getUserInfoListTest(Model model){
        System.out.println("进入mybatis测试。。。");
        List<Map<String,Object>> userInfoList = testService.selectUserInfoAllTest();
        System.out.println("userInfo测试结果：");
        for(int i=0;i<userInfoList.size(); i++){
            System.out.println(userInfoList.get(i));
        }
        model.addAttribute("userInfoList", userInfoList);
        model.addAttribute("loginMessgae", "登陆成功");
        return "jsp/main";
    }
}
