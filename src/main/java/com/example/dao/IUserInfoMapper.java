package com.example.dao;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/18 11:10
 * @File : IUserInfoMapper
 * @Software: IntelliJ IDEA 2019.3.15
 */

public interface IUserInfoMapper {
     List<Map<String,Object>> selectUserInfoAll();
}
