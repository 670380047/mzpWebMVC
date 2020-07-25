package com.example.service;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/20 14:58
 * @File : TestService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.example.dao.IUserInfoMapper;
import com.example.model.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/5/20 14:58
 */
@Service
public class TestService {
    private static Logger logger = Logger.getLogger(TestService.class);
    @Autowired
    IUserInfoMapper userInfoMapper;

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


    @Transactional
    public int insertUserInfo(UserInfo userInfo){
        int flag;
        // spring事务管理@Transactional  这里不需要做  try .... catch。  否则事务捕获不到异常。
        flag = userInfoMapper.insertUserInfo(userInfo);
        System.out.println("userInfo对应的自增长的ID="+userInfo.getId());
        int n = 1/0;

        return  flag;
    }
}
