package com.example.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/11 10:46
 * @File : zyqController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/7/11 10:46
 */
@Controller
public class zyqController {
    @ResponseBody
    @RequestMapping(value = "queryTest",method = {RequestMethod.POST,RequestMethod.GET})
    public UserInfo query(@RequestBody UserInfo userInfo,@RequestParam("urlName") String urlName){
        System.out.println("进入ajax测试控制层:"+userInfo.getUsername()+","+urlName);
        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         *
         * readValue(json串，需要转换成的java对象.class)   如：readValue(json,UserInfo.class)，把json串转换为UserInfo对象
         *
         */
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonObject = null;
//        try {
//            //jackson把java对象转为json串的关键（序列化）
//             jsonObject = objectMapper.writeValueAsString(userInfo);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            System.out.println("java对象转成json串失败");
//        }
//        System.out.println("进入ajax测试控制层:"+jsonObject);
//        return jsonObject;
        System.out.println(userInfo);
        return userInfo;
    }
//    ,@RequestParam("name") String urlName


    @RequestMapping("ajaxPage")
    public String toAjaxPage(){
        return "jsp/test";
    }
}
