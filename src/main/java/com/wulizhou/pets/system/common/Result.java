package com.wulizhou.pets.system.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result {

    private Integer code;
    private String message;
    private Object data;

    public static Result ok(){
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static Result ok(Object data){
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static Result fail(ResultCode code, Object... messages){
        return new Result(code.getCode(), messages == null || messages.length == 0 ? code.getMessage() : String.format(code.getMessage(), messages), null);
    }

}
