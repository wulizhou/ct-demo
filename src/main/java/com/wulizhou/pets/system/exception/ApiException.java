package com.wulizhou.pets.system.exception;

import com.wulizhou.pets.system.common.ResultCode;

public class ApiException extends RuntimeException {

    private final ResultCode result;

    public ApiException(ResultCode result) {
        super(result.getMessage());
        this.result = result;
    }

    public ResultCode getResultCode() {
        return result;
    }

    public static ApiException create(ResultCode result) {
        return new ApiException(result);
    }


}
