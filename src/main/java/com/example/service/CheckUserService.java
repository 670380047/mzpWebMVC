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
