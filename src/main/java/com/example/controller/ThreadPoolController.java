package com.example.controller;

import com.example.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* 
* @Author maozp3
* @Date: 22:51 2021/3/13
* @Param
* @return 
**/ 
@Controller
@RequestMapping("/threadPool")
public class ThreadPoolController {
    @Autowired
    private ThreadPoolService threadPoolService;

    @RequestMapping("/get")
    @ResponseBody
    public List getThreadPool() {
        List threadPool = threadPoolService.getThreadPool(2);
        return threadPool;
    }
}
