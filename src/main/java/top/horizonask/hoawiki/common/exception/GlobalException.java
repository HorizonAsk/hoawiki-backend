package top.horizonask.hoawiki.common.exception;

import cn.hutool.http.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.horizonask.hoawiki.common.ApiStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException{

    /**
     * Exception message, for http response.
     */
    private String message;

    /**
     * Exception code, for http response. Default 500.
     */
    private ApiStatus apiStatus = ApiStatus.API_RESPONSE_ERROR;

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
     * @param apiStatus 响应状态码
     */
    public GlobalException(String message, ApiStatus apiStatus) {
        super(message);
        this.message = message;
        this.apiStatus = apiStatus;
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
     * @param apiStatus 响应状态码
     * @param e 异常对象
     */
    public GlobalException(String message, ApiStatus apiStatus, Throwable e) {
        super(message, e);
        this.message = message;
        this.apiStatus = apiStatus;
    }
}