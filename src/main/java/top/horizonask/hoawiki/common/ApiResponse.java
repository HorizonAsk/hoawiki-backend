package top.horizonask.hoawiki.common;

import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.http.HttpStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {
    /**
     * Api Server StatusCode, see `ApiStatus`.
     **/
    @Getter(AccessLevel.MODULE)
    private ApiStatus apiStatus;

    public Integer getApiCode(){
        return this.apiStatus.getCode();
    }

    /**
     * Response success or not.
     */
    private Boolean success;

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
     * @param apiStatus
     * @param message
     */
    private ApiResponse(Boolean success, ApiStatus apiStatus, String message) {
        this.success = success;
        this.apiStatus = apiStatus;
        this.message = message;
    }

    /**
     * Default success response
     *
     * @return Success ApiResponse 200
     */
    public static ApiResponse ok() {
        return new ApiResponse(true, ApiStatus.API_RESPONSE_SUCCESS, ApiStatus.API_RESPONSE_SUCCESS.getMessage());
    }

    /**
     * Custom success response
     *
     * @param apiStatus
     * @param message
     * @return Custom Success ApiResponse
     */
    public static ApiResponse ok(ApiStatus apiStatus, String message) {
        return new ApiResponse(true, apiStatus, message);
    }

    /**
     * Default failed response
     *
     * @return Failed ApiResponse 500
     */
    public static ApiResponse error() {
        return new ApiResponse(false, ApiStatus.API_RESPONSE_ERROR, ApiStatus.API_RESPONSE_ERROR.getMessage());
    }

    /**
     * Custom failed response
     *
     * @param apiStatus
     * @param message
     * @return Custom Failed ApiResponse
     */
    public static ApiResponse error(ApiStatus apiStatus, String message) {
        return new ApiResponse(false, apiStatus, message);
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
     * @param apiStatus 响应状态类
     * @return 当前实例对象
     */
    public ApiResponse apiStatus(ApiStatus apiStatus) {
        this.setApiStatus(apiStatus);
        this.message = this.apiStatus.getMessage();
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

    @Override
    public String toString() {
        JSONObject resultJson = JSONUtil.createObj();
        resultJson
                .set("apiCode", this.getApiStatus().getCode())
                .set("message", this.getApiStatus().getMessage())
                .putAll(this.data);
        return JSONUtil.toJsonStr(resultJson);
    }
}
