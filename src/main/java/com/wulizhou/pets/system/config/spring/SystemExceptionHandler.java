package com.wulizhou.pets.system.config.spring;

import com.wulizhou.pets.system.common.Result;
import com.wulizhou.pets.system.common.ResultCode;
import com.wulizhou.pets.system.exception.ApiException;
import com.wulizhou.pets.system.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemExceptionHandler.class);

    /**
     * Api业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    public Result handleTokenAuthException(ApiException e) {
        LOGGER.error("业务异常", e);
        return Result.fail(e.getResultCode());
    }

    /**
     * 无效的token
     * @param e
     * @return
     */
    @ExceptionHandler(TokenException.class)
    public Result handleTokenException(TokenException e) {
        LOGGER.error("无效的token");
        return Result.fail(ResultCode.TOKEN_ERROR);
    }

    /**
     * 参数错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, MissingServletRequestParameterException.class})
    public Result handleMethodArgumentNotValidException(Exception e) {
        LOGGER.error("参数检验失败", e);
        if (e instanceof MethodArgumentNotValidException) {
            // raw下验证未通过, @RequestBody时发生
            FieldError fieldError = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError();
            return Result.fail(ResultCode.PARAMETER_ERROR, fieldError.getField() + fieldError.getDefaultMessage());
        } else if (e instanceof BindException) {
            // x-www-form-urlencoded验证未通过，表单校验异常
            FieldError fieldError = ((BindException) e).getBindingResult().getFieldError();
            return Result.fail(ResultCode.PARAMETER_ERROR, fieldError.getField() + fieldError.getDefaultMessage());
        } else {
            return Result.fail(ResultCode.PARAMETER_ERROR, e.getMessage());
        }
    }

    /**
     * 400
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("参数解析失败", e);
        return Result.fail(ResultCode.ERROR_400004);
    }

    /**
     * 405
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("不支持的请求方式", e);
        return Result.fail(ResultCode.ERROR_400005);
    }

    /**
     * 不支持的媒体格式
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        LOGGER.error("不支持的媒体类型", e);
        return Result.fail(ResultCode.ERROR_400006);
    }

    /**
     * 其它未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException( Exception e) {
        LOGGER.error("未知异常", e);
        return Result.fail(ResultCode.ERROR);
    }

}
