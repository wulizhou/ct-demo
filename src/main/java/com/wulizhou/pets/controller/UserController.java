package com.wulizhou.pets.controller;


import com.wulizhou.pets.model.dao.User;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/add")
    public Result add() {
        User user = new User();
        user.setAvatar("").setMobile("18123906660").setUsername("wulizhou");
        userService.add(user);
        return Result.ok(user.getId());
    }

}
