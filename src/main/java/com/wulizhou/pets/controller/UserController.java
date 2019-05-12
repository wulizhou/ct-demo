package com.wulizhou.pets.controller;


import com.wulizhou.pets.service.facade.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
    /*@PostMapping("/add")
    public Result add() {
        User user = new User();
        user.setAvatar("").setMobile("18123906660").setUserName("wulizhou");
        userService.save(user);
        return Result.ok(user.getId());
    }*/

}
