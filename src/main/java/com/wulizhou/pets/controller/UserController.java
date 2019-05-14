package com.wulizhou.pets.controller;


import com.wulizhou.pets.service.facade.Constants;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.Result;
import com.wulizhou.pets.system.common.ResultCode;
import com.wulizhou.pets.system.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @GetMapping("/getVerificationCode")
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
        String token = userService.login(phone,code);
        CookieUtils.setCookie(request, response, Constants.COOKIE_NAME, token, Constants.TOKEN_COOKIE_MAX_AGE);
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
     * @param operation like：点赞 ，unlike：取消点赞
     * @return
     */
    @PostMapping("/like")
    public Result like(@RequestParam("petId") Integer petId,@RequestParam("operation") String operation,HttpServletRequest request) {
        Integer result = userService.like(petId,operation,request);
        return Result.ok(result > 0 ? "操作成功":"");
    }

    /**
     * 收藏 宠物或者宠物用品
     * 需要联合两个表查询两个表的数据
     * @param id - 是petId或者petSupplyId
     * @param operation l：点赞 ，u：取消点赞
     * @param type 类型：判断是宠物还是用品 pet ,supplies
     * @return
     */
    @PostMapping("/collect")
    public Result collect(@RequestParam("id") Integer id,@RequestParam("operation") String operation,@RequestParam("type") String type) {
//        userService.collect();
        return Result.ok();
    }

    /**
     * 获取点赞数据
     * @return
     */
    /*@GetMapping("/getLike")
    public Result getLike() {
        userService.getLike();
        return Result.ok();
    }*/

    /**
     * 获取收藏数据
     * @return
     */
    /*@GetMapping("/getCollect")
    public Result getCollect() {
        userService.getCollect();
        return Result.ok();
    }*/

    /**
     * 获取点赞总条数
     * @return
     */
    /*@GetMapping("/getLikeCount")
    public Result getLikeCount() {
        userService.getLikeCount();
        return Result.ok();
    }*/

    /**
     * 获取收藏总条数
     * @return
     */
    /*@GetMapping("/getCollectCount")
    public Result getCollectCount() {
        userService.getCollectCount();
        return Result.ok();
    }*/

}
