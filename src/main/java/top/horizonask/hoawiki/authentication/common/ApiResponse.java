package top.horizonask.hoawiki.authentication.common;

import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.http.HttpStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {

//    private static final long serialVersionUID = 8993485788201922830L;

    /**
     * Response success or not.
     */
    private Boolean success;

    /**
     * Response Code.
     * 200: Normal;
     * 500: Exception.
     */
    private Integer code;

    /**
     * Response Message
     */
    private String message;

    public JSONObject getData() {
        return data;
    }

    /**
     * Response data.
     */
    private final JSONObject data = JSONUtil.createObj();

    /**
     * Default Constructor
     */
    private ApiResponse() {
    }

    /**
     * Private Constructor
     *
     * @param success
     * @param code
     * @param message
     */
    private ApiResponse(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * Default success response
     *
     * @return Success ApiResponse 200
     */
    public static ApiResponse ok() {
        return new ApiResponse(true, HttpStatus.HTTP_OK, "success");
    }

    /**
     * Custom success response
     *
     * @param success
     * @param code
     * @param message
     * @return Custom Success ApiResponse
     */
    public static ApiResponse ok(Boolean success, Integer code, String message) {
        return new ApiResponse(success, code, message);
    }

    /**
     * Default failed response
     *
     * @return Failed ApiResponse 500
     */
    public static ApiResponse error() {
        return new ApiResponse(false, HttpStatus.HTTP_INTERNAL_ERROR, "error");
    }

    /**
     * Custom failed response
     *
     * @param success
     * @param code
     * @param message
     * @return Custom Failed ApiResponse
     */
    public static ApiResponse error(Boolean success, Integer code, String message) {
        return new ApiResponse(success, code, message);
    }

    /**
     * 自定义响应是否成功
     *
     * @param success 响应是否成功
     * @return 当前实例对象
     */
    public ApiResponse success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 自定义响应状态码
     *
     * @param code 响应状态码
     * @return 当前实例对象
     */
    public ApiResponse code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 自定义响应消息
     *
     * @param message 响应消息
     * @return 当前实例对象
     */
    public ApiResponse message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 自定义响应数据，一次设置一个 map 集合
     *
     * @param jsonObject 响应数据
     * @return 当前实例对象
     */
    public ApiResponse data(JSONObject jsonObject) {
        this.data.putAll(jsonObject);
        return this;
    }

    /**
     * 通用设置响应数据，一次设置一个 key - value 键值对
     *
     * @param key   键
     * @param value 数据
     * @return 当前实例对象
     */
    public ApiResponse data(String key, Object value) {
        this.data.set(key, value);
        return this;
    }

    /**
     * 通用设置响应数据，可用于列举数据，纳入同一个key下成为array.
     *
     * @param key   键
     * @param value 数据
     * @return 当前实例对象
     */
    public ApiResponse accumulate(String key, Object value) {
        this.data.accumulate(key, value);
        return this;
    }
}
