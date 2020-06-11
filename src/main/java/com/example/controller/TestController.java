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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private static Logger logger = Logger.getLogger(TestController.class);
    @Autowired
    TestService testService;
    @Autowired
    CheckUserService checkUserService;

    HttpSession session;

    /**
     * 记录Form表单传输，后台接收的几种方式
     * 1. HttpServletRequest request 中，用request.getParameter("username")获取、
     * 2. @RequestParam Map map 中，使用@RequestParam
     * 3. 直接使用"包含form表单的中字段"的实体类。例如这里的 UserInfo类
     * 4. 直接定义基本数据类型/包装类型：   1、提交表单的name和参数的名称必须相同。2、严格区分大小写
     * @param request
     * @param map
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/welcome",method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam Map map,  UserInfo userInfo, String username, String password){
        map.put("username",request.getParameter("username"));
        map.put("password",request.getParameter("password"));
        session = request.getSession();
        session.setAttribute("LoginMessageMap",map);
        if(checkUserService.checkUser(map)){
            System.out.println("进入主页。。。。。。");
            logger.debug("进入主页.............");
//            logger.info("进入主页.............");
            logger.warn("进入主页.............");
            //mybatis
           /*
           List<UserInfo> userInfoList = testService.selectUserInfoAll();
            //这里只是为了和分页共用一个也而多封了一层map,并且key就是"list"
            Map<String,List<UserInfo>> mapList = new HashMap<>();
            mapList.put("list", userInfoList);
            map.put("userInfoList", mapList);   //未完成，未向页面传值
            map.put("message","登陆成功");
            */
            return "redirect:getAll?start=1";
        }
        map.put("message","用户名密码不正确");
        return "jsp/login";

    }


    @RequestMapping("getAll")
    public String getUserInfoList(Model model,@RequestParam(value = "start",defaultValue = "0") int start,
                                 @RequestParam(value = "size",defaultValue = "3") int size){
        System.out.println("进入mybatis测试。。。");
        //开启分页
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


    @RequestMapping("insert")
    public String insert(UserInfo userInfo){
        System.out.println(userInfo);
        System.out.println("测试获取自增长序列(sql执行之前)：ID="+userInfo.getId());
        testService.insertUserInfo(userInfo);
        System.out.println("测试获取自增长序列（sql执行之后）：ID="+userInfo.getId());
        return "redirect:getAll";
    }


    @RequestMapping("testAspect")
    @ResponseBody
    public String testAspect(){
        return "测试aop";
    }
}
