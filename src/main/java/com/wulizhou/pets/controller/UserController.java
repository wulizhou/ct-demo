package com.wulizhou.pets.controller;


import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志操作记录测试
 * @author Administrator
 *
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/hello")
    public Result sayHello() {
        return Result.ok("hello world");
    }

}
