package com.example.controller;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/11 10:46
 * @File : zyqController
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println("userInfo:"+userInfo);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = null;
        try {
            //因为前面用对象接受了前台传过来的json串，最终为了转成map（json串到map），所以这里多了一步把对象转换再为json串的步骤
            //list、map、java对象转json串，都是这个操作。结果为String
            jsonObject = objectMapper.writeValueAsString(userInfo);
            System.out.println("json串"+jsonObject);
            //把json串转换为map
            Map<String,String> jsonToMap = objectMapper.readValue(jsonObject, HashMap.class);
            System.out.println("name="+jsonToMap.get("username"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
