package com.wulizhou.pets.system.common;


import lombok.Getter;

@Getter
public enum ResultCode {

    // 公共返回码
    SUCCESS(200, "请求成功"),
    SYSTEM_BUSY(-1, "系统繁忙，请稍后重试..."),
    TOKEN_ERROR(400001, "登录信息已过期，请重新登录..."),
    PARAMETER_ERROR(400002, "参数错误：%s"),
    ERROR(400003, "未知异常"),
    ERROR_400004(400004, "参数解析失败"),
    ERROR_400005(400005, "不支持的请求方式"),
    ERROR_400006(400006, "不支持的媒体类型"),
    ERROR_400007(400007, "非法请求"),
    ERROR_400009(400008, "业务异常: %s"),


    ;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;


}
