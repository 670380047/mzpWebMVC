package com.example.service;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/20 14:58
 * @File : TestService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.dao.IUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/5/20 14:58
 */
@Service
public class TestService {
    @Autowired
    IUserInfoMapper userInfoDao;


    public List selectUserInfoAll(){
        List list = userInfoDao.selectUserInfoAll();
        return list ;
    }

    public List selectUserInfoAllTest(){
        System.out.println("进入service层了");
        List list = userInfoDao.test(4);
        System.out.println("list是null还是0？ "+list.toString());
        return list ;
    }
}