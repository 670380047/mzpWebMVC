package com.example.controller;

import com.example.model.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/16 16:53
 * @File : MzpController
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Controller
@RequestMapping("/mzp")
public class MzpController {
    //添加一个日志器
    private static final Logger logger = Logger.getLogger(MzpController.class);

    /**
     * 第一个测试页面
     *
     * @return 返回test页面
     */
    //多个url映射到同一个方法
    @RequestMapping(value = {"/index", "/index1", "/index2"})
    //没有ModelAndView时,默认返回的String类型就是视图名。即这里的"jsp/test"
    public String index() {
        //输出日志文件
        System.out.println("测试数据1");
        logger.info("mzp's first jsp pages");
        System.out.println("测试数据2");
        return "jsp/test";
    }

    // /mv/{test}/model  这里的{test}是占位符，输入任意路径都可以。如/mv/1/model     /mv/hhh/model
    @RequestMapping("/mv/{test}/model")
    public ModelAndView mv() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jsp/mv");
        //mv.addObject("这个setView");
        mv.addObject("model", "我的模型热部署");
        //输出日志文件
        logger.info("mzp's ModelAndView");
        return mv;
    }

    /**
     * 测试response返回页面数据
     *
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/mv1" ,produces = "application/json;charset=utf-8")
    public ModelAndView mv1(HttpServletResponse response) throws IOException {
        //mv.addObject("这个setView");
        //输出日志文件
        logger.info("mzp's ModelAndView");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write("毛宗鹏");
        /*直接在处理器/控制器里写响应，可以通过返回null来告诉dispatcherServlet,自己已经做出响应了，
        不需要它进行初图解析了*/
        return null;
    }

    //只接受post类型的请求
    @RequestMapping(value = "/string",method = RequestMethod.POST)
    @ResponseBody()
    public String toStr() {
        System.out.println("字符串测试");
        return "毛宗鹏123";
    }

    @RequestMapping("/handle1")
    public ModelAndView HandleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jsp/handle");
        /*// 做出响应之后，返回return null。告诉前端控制器，不需要他再做视图解析了
        response.setContentType("application/xml;charset=utf-8");
        response.getWriter().write("handla1客户端请求测试");
        */
        System.out.println("==========TestController");
        return mv;
    }

    @RequestMapping(value = "/model")
    //除了使用ModelAndView方式外。还可以使用Map、Model和ModelMap来向前台页面创造
    //　　使用后面3种方式，都是在方法参数中，指定一个该类型的参数.  效果一样，一般使用map就可以了
    public ModelAndView modelTest(Model model, Map map, ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        //ModelAndView是在视图渲染之前才执行的，此处添加的modelMap虽然在前面，但是最后会覆盖下面添加的modelMap
        mv.addObject("modelMap","渲染前执行");

        mv.setViewName("jsp/model");
        //在Model中添加的属性会跟随ModelAndView
        map.put("map1","小毛map");
        map.put("mzp1","毛宗鹏");
        model.addAttribute("model","毛宗鹏model");
        //model.addAllAttributes(map);   //不需要
        modelMap.addAttribute("modelMap","毛宗鹏modelMap");
        //AnnotationMethodHandlerAdapter和RequestMappingHandlerApapter将使用
        //BindingAwareModelMap作为模型对象的实现。即方法中传入的三个形参都是同一个对象。
        System.out.print("model==map? ");
        System.out.println(model==map);  //true
        System.out.print("map==modelMap? ");
        System.out.println(map==modelMap);   //true
        return mv;
    }

    @RequestMapping(value = "/noModelAndView")
    //没有ModelAndView时,默认返回的String类型就是视图名。即这里的"jsp/model"
    public  String view(Map map){
        map.put("map1","没有ModelAndView");
        return "jsp/model";
    }

    //注解了@ModelAttribute的方法会在功能处理方法@RequestMapping之前执行
    //并且把UserInfo实体对象添加到功能处理方法的入参Model（数据模型）中去,
    //实体对象的名字就是@ModelAttribute("UserInfo123")的参数值UserInfo123
    @ModelAttribute("UserInfo123")
    public UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("毛宗鹏");
        userInfo.setPassword("123");
        userInfo.setMyAge(24);
        System.out.println("UserInfo已执行（在@RequestMapping之前）！实体类UserInfo123已经注入到Model中");
        return userInfo;
    }

    @RequestMapping(value = "modelAttr")
    public String testModelAttribute(Model model){
        return "jsp/test/moderAttr";
    }

    @RequestMapping(value = "modelAttr2")
    //这里的入参中出现了@ModelAttribute("UserInfo123"),入参处使用该注解是为了从模型数据（这里就是UserInfo123）里面取值，然后赋值给入参。
    //1.功能处理方法（注解了@RequestMapping的方法）执行前，会先执行注解了@ModelAttribute()的方法！是方法！（这个方法
    // 是往数据模型Model中赋对象的=====》模型数据绑定）    不是入参！ 不是注解入参上的那个，而是注解在方法上的那个！
    //2.然后在执行功能处理方法，当发现功能处理方法的入参中也有@ModelAttribute()时，在进行模型数据绑定时，会先检查数据模型（Model）
    //是否已经有同名对象。如果已经有了，就直接使用，覆盖掉之前方法处赋值的结果，而采用入参处赋值的结果（如果URL中没有参数，就不覆盖）。
    //如果没有的话，就通过反射，创建一个。  （方法处优先执行=====》但可能会被入参处的覆盖（如果RUL中有参数时，覆盖！））
    //3.所以！实际上，这里入参处的@ModelAttribute("UserInfo123")会被上面的方法处注解了@ModelAttribute("UserInfo123")的抢先一步，
    // 现在他俩是一回事！！！
    public String testModelAttribute2(@ModelAttribute("UserInfo123") UserInfo userInfo){
        //如果url上没有对应的实体类的属性值，则这些值都来自于注解了@ModelAttribute("UserInfo123")的方法
        System.out.println("姓名："+userInfo.getUsername());
        System.out.println("年龄："+userInfo.getMyAge());
        return "jsp/test/moderAttr";
    }
}