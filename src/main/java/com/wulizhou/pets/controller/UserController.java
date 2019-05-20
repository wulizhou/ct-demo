package com.wulizhou.pets.controller;


import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.session.LoginUserManager;
import com.wulizhou.pets.session.SessionUtil;
import com.wulizhou.pets.system.common.Result;
import com.wulizhou.pets.system.common.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日志操作记录测试
 * @author Administrator
 *
 */
@Api(tags = "用户controller")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private LoginUserManager manager;

    /**
     * 发送验证码
     * @return
     */
    @ApiOperation(value="获取验证码")
    @PostMapping("/getVerificationCode")
    public Result getVerificationCode(@ApiParam(name="phone",value="用户手机",required=true)@RequestParam("phone") String phone) {
        String verificationCode =  userService.getVerificationCode(phone);
        return verificationCode != null ? Result.ok(verificationCode) : Result.fail(ResultCode.ERROR_400009);
    }

    /**
     * 登录 - 对验证码进行检验
     * @return
     */
    @ApiOperation(value="登录")
    @PostMapping("/login")
    public Result login(@ApiParam(name="phone",value="用户手机",required=true)@RequestParam("phone") String phone,
                        @ApiParam(name="code",value="验证码",required=true)@RequestParam("code") String code,
                        HttpServletRequest request) {
        //需要配合短信接口做
        String token = userService.login(phone,code,request);
        return token != null ? Result.ok(token) : Result.fail(ResultCode.ERROR_400010);
    }

    @ApiOperation(value="退出")
    @PostMapping("/logout")
    public Result logout() {
        // 清除登录信息
        manager.deleteByUserId(SessionUtil.getCurrentUserId());
        return Result.ok();
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
    @ApiOperation(value="点赞 仅点赞宠物")
    @PostMapping("/like")
    public Result like(@ApiParam(name="petId",value="宠物ID",required=true)@RequestParam("petId") Integer petId,
                       @ApiParam(name="operation",value="操作：1-点赞 ，0-取消点赞",required=true)@RequestParam("operation") Integer operation) {
        Integer result = userService.like(petId,operation);
        return Result.ok(result > 0 ? "操作成功":"数据已存在，请勿重复发送");
    }

    /**
     * 收藏 宠物或者宠物用品
     * 需要联合两个表查询两个表的数据
     * @param id - 是petId或者petSupplyId
     * @param operation l：收藏 ，0：取消收藏
     * @param type 类型：判断是宠物还是用品 pet ,supplies
     * @return
     */
    @ApiOperation(value="收藏 宠物或者宠物用品")
    @PostMapping("/collect")
    public Result collect(@ApiParam(name="id",value="宠物ID或宠物用品ID",required=true)@RequestParam("id") Integer id,
                          @ApiParam(name="operation",value="操作：l-收藏 ，0-取消收藏",required=true)@RequestParam("operation") Integer operation,
                          @ApiParam(name="type",value="类型：宠物-pet ,宠物用品-supplies",required=true)@RequestParam("type") String type) {
        Integer result = userService.collect(id,operation,type);
        return Result.ok(result > 0 ? "操作成功":"数据已存在，请勿重复发送");
    }

    /**
     * 获取点赞数据
     * @return
     */
    @ApiOperation(value="获取点赞数据")
    @PostMapping("/getLike")
    public Result getLike() {
        List<Pets> pets = userService.getLike();
        return Result.ok(pets);
    }

    /**
     * 获取收藏数据
     * @return
     */
    @ApiOperation(value="获取收藏数据")
    @PostMapping("/getCollect")
    public Result getCollect() {
        List<Object> list = userService.getCollect();
        return Result.ok(list);
    }

    /**
     * 获取点赞总条数 - 点赞的只有宠物
     * @return
     */
    @ApiOperation(value="获取点赞总条数")
    @PostMapping("/getLikeCount")
    public Result getLikeCount() {
        Integer count = userService.getLikeCount();
        return Result.ok(count);
    }

    /**
     * 获取收藏总条数 - 收藏的包括宠物和用品
     * @return
     */
    @ApiOperation(value="获取收藏总条数")
    @PostMapping("/getCollectCount")
    public Result getCollectCount() {
        Integer count = userService.getCollectCount();
        return Result.ok(count);
    }

}
