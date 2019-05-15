package com.wulizhou.pets.controller;


import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.Result;
import com.wulizhou.pets.system.common.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
     * 发送验证码
     * @return
     */
    @PostMapping("/getVerificationCode")
    public Result getVerificationCode(@RequestParam("phone") String phone) {
        String verificationCode =  userService.getVerificationCode(phone);
        return verificationCode != null ? Result.ok(verificationCode) : Result.fail(ResultCode.ERROR_400009);
    }

    /**
     * 登录 - 对验证码进行检验
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam("phone") String phone, @RequestParam("code") String code,
                        HttpServletRequest request, HttpServletResponse response) {
        //需要配合短信接口做
        String token = userService.login(phone,code,request);
//        CookieUtils.setCookie(request, response, Constants.COOKIE_NAME, token, Constants.TOKEN_COOKIE_MAX_AGE);
        return token != null ? Result.ok(token) : Result.fail(ResultCode.ERROR_400010);
    }

    /**
     * 更改手机号
     * @return
     */
    /*@PutMapping("/updatePhone")
    public Result updatePhone(@RequestParam("phone") String phone,@RequestParam("code") String code) {
        //需要配合短信接口做
        userService.updatePhone();
        return Result.ok();
    }*/

    /**
     *
     * @param petId
     * @param operation 1：点赞 ，0：取消点赞
     * @return
     */
    @PostMapping("/like")
    public Result like(@RequestParam("petId") Integer petId,@RequestParam("operation") Integer operation,HttpServletRequest request) {
        Integer result = userService.like(petId,operation,request);
        return Result.ok(result > 0 ? "操作成功":"");
    }

    /**
     * 收藏 宠物或者宠物用品
     * 需要联合两个表查询两个表的数据
     * @param id - 是petId或者petSupplyId
     * @param operation l：收藏 ，0：取消收藏
     * @param type 类型：判断是宠物还是用品 pet ,supplies
     * @return
     */
    @PostMapping("/collect")
    public Result collect(@RequestParam("id") Integer id,@RequestParam("operation") Integer operation,@RequestParam("type") String type,HttpServletRequest request) {
        Integer result = userService.collect(id,operation,type,request);
        return Result.ok(result > 0 ? "操作成功":"");
    }

    /**
     * 获取点赞数据
     * @return
     */
    @PostMapping("/getLike")
    public Result getLike(HttpServletRequest request) {
        List<Pets> pets = userService.getLike(request);
        return Result.ok(pets);
    }

    /**
     * 获取收藏数据
     * @return
     */
    @PostMapping("/getCollect")
    public Result getCollect(HttpServletRequest request) {
        List<Object> list = userService.getCollect(request);
        return Result.ok(list);
    }

    /**
     * 获取点赞总条数 - 点赞的只有宠物
     * @return
     */
    @PostMapping("/getLikeCount")
    public Result getLikeCount(HttpServletRequest request) {
        Integer count = userService.getLikeCount(request);
        return Result.ok(count);
    }

    /**
     * 获取收藏总条数 - 收藏的包括宠物和用品
     * @return
     */
    @PostMapping("/getCollectCount")
    public Result getCollectCount(HttpServletRequest request) {
        Integer count = userService.getCollectCount(request);
        return Result.ok(count);
    }

}
