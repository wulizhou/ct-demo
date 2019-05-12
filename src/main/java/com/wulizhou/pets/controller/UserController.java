package com.wulizhou.pets.controller;


import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 登录
     * @return
     */
    @PostMapping("/getCode")
    public Result getCode(@RequestParam("petId") String petId) {
        //需要配合短信接口做
        String token = userService.login(user);
        return Result.ok(token);
    }

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result login(User user) {
        //需要配合短信接口做
        String token = userService.login(user);
        return Result.ok(token);
    }

    /**
     * 更改手机号
     * @return
     */
    @PutMapping("/updatePhone")
    public Result updatePhone(User user) {
        //需要配合短信接口做
        userService.updatePhone();
        return Result.ok();
    }

    /**
     * 点赞
     * @return
     */
    @PostMapping("/like")
    public Result like(@RequestParam("petId") Integer petId,@RequestParam("operation") String operation) {
        userService.like(petId,operation);
        return Result.ok();
    }

    /**
     * 收藏 宠物或者宠物用品
     * 需要联合两个表查询两个表的数据
     * @return
     */
    @PostMapping("/collect")
    public Result collect(@RequestParam("petType") String petType,@RequestParam("operation") String operation) {
        userService.collect();
        return Result.ok();
    }

    /**
     * 获取点赞数据
     * @return
     */
    @GetMapping("/getLike")
    public Result getLike() {
        userService.getLike();
        return Result.ok();
    }

    /**
     * 获取收藏数据
     * @return
     */
    @GetMapping("/getCollect")
    public Result getCollect() {
        userService.getCollect();
        return Result.ok();
    }

    /**
     * 获取点赞总条数
     * @return
     */
    @GetMapping("/getLikeCount")
    public Result getLikeCount() {
        userService.getLikeCount();
        return Result.ok();
    }

    /**
     * 获取收藏总条数
     * @return
     */
    @GetMapping("/getCollectCount")
    public Result getCollectCount() {
        userService.getCollectCount();
        return Result.ok();
    }

    /**
     * 获取收藏总条数
     * @return
     */
    @GetMapping("/getCollectCount")
    public Result getCollectCount() {
        userService.getCollectCount();
        return Result.ok();
    }
}
