package com.wulizhou.pets.system.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> ok(){
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> ok(T data){
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> fail(ResultCode code, Object... messages){
        return new Result(code.getCode(), messages == null || messages.length == 0 ? code.getMessage() : String.format(code.getMessage(), messages), null);
    }

}
