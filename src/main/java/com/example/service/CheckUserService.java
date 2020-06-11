package com.example.service;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/28 14:40
 * @File : CheckUserService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/28 14:40
 */
@Service
    // 声明周期默认是单例模式singleton。  prototype是原型，每次调用都会生成一个bean实例
//@Scope(value = "prototype")
    // 这个bean是否懒加载。默认是true(即：只要加了这个注解，就会懒加载，主要就是为了加快容器启动时间)。
    // 懒加载就是：容器启动的时候不加载，调用的时候才加载
//@Lazy(value = false)
public class CheckUserService {
    private static Logger logger = LoggerFactory.getLogger(CheckUserService.class);

    public boolean checkUser(Map map){
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if("mzp".equals(username) && "123".equals(password)){
            return true;
        }
            return false;
    }


}
