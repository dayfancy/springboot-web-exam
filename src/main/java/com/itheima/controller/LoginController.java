package com.itheima.controller;

import com.itheima.common.Result;
import com.itheima.entity.Emp;
import com.itheima.entity.LoginInfo;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录Controller
 */
@Slf4j
@RestController
@SuppressWarnings("all")
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录: {}", emp);
        LoginInfo info =  empService.login(emp);
        return Result.success(info);
    }

}
