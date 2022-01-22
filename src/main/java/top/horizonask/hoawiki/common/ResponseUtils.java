package top.horizonask.hoawiki.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 2:41
 */

@Slf4j
@Data
public class ResponseUtils implements Serializable {

    private Integer apiCode;

    private String message;

    private JSONObject data = JSONUtil.createObj();

    private ResponseUtils() {
    }

    /**
     * 自定义响应状态码
     *
     * @param apiStatus 响应状态类
     * @return 当前实例对象
     */
    public ResponseUtils apiStatus(ApiStatus apiStatus) {
        this.setApiCode(apiStatus.getCode());
        this.setMessage(apiStatus.getMessage());
        return this;
    }

    /**
     * 自定义响应消息
     *
     * @param message 响应消息
     * @return 当前实例对象
     */
    public ResponseUtils message(String message) {
        this.setMessage(message);
        return this;
    }

    /**
     * 自定义响应数据，一次设置一个 map 集合
     *
     * @param jsonObject 响应数据
     * @return 当前实例对象
     */
    public ResponseUtils data(JSONObject jsonObject) {
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
    public ResponseUtils data(String key, Object value) {
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
    public ResponseUtils accumulate(String key, Object value) {
        this.data.accumulate(key, value);
        return this;
    }

    public static void responseJson(HttpServletResponse response, ResponseUtils responseUtils) {
        PrintWriter out = null;
        try {
            response.setStatus(ApiStatus.fromCode(responseUtils.getApiCode()).getHttpStatusCode());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(responseUtils);
        } catch (Exception e) {
            log.error("Response to Json exception: " + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static ResponseUtils response(ApiStatus apiStatus, JSONObject data) {
        return new ResponseUtils().apiStatus(apiStatus).data(data);
    }

    public static ResponseUtils response(ApiStatus apiStatus) {
        return new ResponseUtils().apiStatus(apiStatus);
    }

    public static ResponseUtils success() {
        return ResponseUtils.response(ApiStatus.API_RESPONSE_SUCCESS);
    }

    public static ResponseUtils success(JSONObject data) {
        return ResponseUtils.response(ApiStatus.API_RESPONSE_SUCCESS, data);
    }

    public static ResponseUtils success(ApiStatus apiStatus) {
        return ResponseUtils.response(apiStatus);
    }

    public static ResponseUtils success(ApiStatus apiStatus, JSONObject data) {
        return ResponseUtils.response(apiStatus, data);
    }

    public static ResponseUtils fail(ApiStatus apiStatus) {
        return ResponseUtils.response(apiStatus);
    }

    public static ResponseUtils fail(JSONObject data) {
        return ResponseUtils.response(ApiStatus.API_RESPONSE_ERROR, data);
    }

    public static ResponseUtils fail(ApiStatus apiStatus, String failMessage) {
        return ResponseUtils.response(apiStatus).data("error",failMessage);
    }

    public ResponseEntity<JSONObject> toResponseEntity() {
        return new ResponseEntity<>(
                this.compress(),
                HttpStatus.resolve(ApiStatus.fromCode(this.getApiCode()).getHttpStatusCode()) != null ?
                        HttpStatus.resolve(ApiStatus.fromCode(this.getApiCode()).getHttpStatusCode()) :
                        HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    public JSONObject compress(){
        JSONObject resultJson = JSONUtil.createObj();
        resultJson
                .set("apiCode", this.getApiCode())
                .set("message", this.getMessage())
                .putAll(this.data);
        return resultJson;
    }

    @Override
    public String toString() {
        JSONObject resultJson = JSONUtil.createObj();
        resultJson
                .set("apiCode", this.getApiCode())
                .set("message", this.getMessage())
                .putAll(this.data);
        return JSONUtil.toJsonStr(resultJson);
    }
}
