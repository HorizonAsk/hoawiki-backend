package top.horizonask.hoawiki.authentication.common.exception;

import cn.hutool.http.HttpStatus;

public class GlobalException extends RuntimeException{
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Exception message, for http response.
     */
    private String message;

    /**
     * Exception code, for http response. Default 500.
     */
    private Integer code = HttpStatus.HTTP_INTERNAL_ERROR;

    /**
     * Default Constructor based on exception message.
     * @param message 异常信息
     */
    public GlobalException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Default Constructor based on exception message and code.
     * @param message 异常信息
     * @param code 响应状态码
     */
    public GlobalException(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    /**
     * 根据异常信息，异常对象构建 一个异常实例对象
     * @param message 异常信息
     * @param e 异常对象
     */
    public GlobalException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    /**
     * 根据异常信息，响应状态码，异常对象构建 一个异常实例对象
     * @param message 异常信息
     * @param code 响应状态码
     * @param e 异常对象
     */
    public GlobalException(String message, Integer code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }
}