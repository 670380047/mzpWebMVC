package com.example.module;

import com.example.dao.IUserInfoMapper;
import com.example.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * 在dao和service中间又加了一层。用来保存所有的获取数据库的操作。
 *      因为spring不支持往线程类中注入依赖（注入结果都是null），所以打算用getBean()方式来获取依赖，
 *      但是mybatis的依赖都是接口，名字不固定。
 *      所以在中间又加了一层，包装一下daoo层，专门来获取数据。然后在线程里面获取用getBean来获取这一层。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/13 23:22
 * @File : UserInfoModule
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Repository
public class UserInfoModule {
    @Autowired
    IUserInfoMapper userInfoMapper;

    public List<UserInfo> selectUserInfoAll(){
        return userInfoMapper.selectUserInfoAll();
    }
    public List<Map<String,Object>>  test(long id){
        return userInfoMapper.test(id);
    }

    public int insertUserInfo(UserInfo userInfo){
        return userInfoMapper.insertUserInfo(userInfo);
    }
}
