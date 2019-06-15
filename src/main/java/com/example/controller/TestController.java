package com.example.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/18 11:20
 * @File : TestController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.model.UserInfo;
import com.example.service.CheckUserService;
import com.example.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    @Autowired
    CheckUserService checkUserService;

    @RequestMapping("/welcome")
    public String hello(HttpServletRequest request, Map map ){
        map.put("username",request.getParameter("username"));
        map.put("password",request.getParameter("password"));
        if(checkUserService.checkUser(map)){
            System.out.println("进入主页。。。。。。");
            //mybatis
            List<UserInfo> userInfoList = testService.selectUserInfoAll();
            //这里只是为了和分页共用一个也而多封了一层map,并且key就是"list"
            Map<String,List<UserInfo>> mapList = new HashMap<>();
            mapList.put("list", userInfoList);
            map.put("userInfoList", mapList);   //未完成，未向页面传值
            map.put("message","登陆成功");
            return "jsp/main";
        }
        map.put("message","用户名密码不正确");
        return "jsp/login";

    }


    @RequestMapping("getAll")
    public String getUserInfoList(Model model,@RequestParam(value = "start",defaultValue = "0") int start,
                                 @RequestParam(value = "size",defaultValue = "3") int size){
        System.out.println("进入mybatis测试。。。");
        //开启分页(目前尚未配置)
        PageHelper.startPage(start, size);
        List<UserInfo> userInfoList = testService.selectUserInfoAll();
        //放入pageInfo
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoList);

        System.out.println("pageInfo测试结果：");
        for(int i=0;i<pageInfo.getSize(); i++){
            System.out.println(pageInfo.getList().get(i));
        }
        model.addAttribute("userInfoList", pageInfo);
        model.addAttribute("loginMessage", "登陆成功");
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
        model.addAttribute("loginMessage", "登陆成功");
        return "jsp/main";
    }
}
