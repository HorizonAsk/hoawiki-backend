package top.horizonask.hoawiki.authentication.common;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 2:41
 */

@Slf4j
@Data
@AllArgsConstructor
public class ResponseUtils {

    private Integer code;

    private String msg;

    private Object data;

    public static void responseJson(ServletResponse response, Object data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSONUtil.toJsonStr(data));
        } catch (Exception e) {
            log.error("Response to Json exception: " + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static ResponseUtils response(Integer code,String msg,Object data){
        return new ResponseUtils(code,msg,data);
    }

    public static ResponseUtils success(Object data){
        return ResponseUtils.response(HttpStatus.HTTP_OK, "success", data);
    }

    public static ResponseUtils fail(Object data){
        return ResponseUtils.response(HttpStatus.HTTP_INTERNAL_ERROR, "success", data);
    }
}
