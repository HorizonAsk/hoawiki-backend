package top.horizonask.hoawiki.common;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
@AllArgsConstructor
public class ResponseUtils implements Serializable {

    private Boolean success;

    private Integer apiCode;

    private String message;

    private Object data;

    public static void responseJson(HttpServletResponse response, ResponseUtils responseUtils) {
        PrintWriter out = null;
        try {
            response.setStatus(ApiStatus.fromCode(responseUtils.getApiCode()).getHttpStatusCode());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSONUtil.toJsonStr(responseUtils));
        } catch (Exception e) {
            log.error("Response to Json exception: " + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static ResponseUtils response(Boolean success, ApiStatus apiStatus, Object data) {
        return new ResponseUtils(success,apiStatus.getCode(), apiStatus.getMessage(), data);
    }

    public static ResponseUtils success(Object data) {
        return ResponseUtils.response(true, ApiStatus.API_RESPONSE_SUCCESS, data);
    }

    public static ResponseUtils fail(Object data) {
        return ResponseUtils.response(false,ApiStatus.API_RESPONSE_ERROR, data);
    }
}
