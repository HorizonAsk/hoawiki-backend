package top.horizonask.hoawiki.common;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Getter;

@Getter
public enum ApiStatus implements IStatus {


    // 操作成功 20XXX
    /**
     * 操作成功！
     */
    API_RESPONSE_SUCCESS(20000, "操作成功！"),

    // 新增数据成功 202XX
    /**
     * 用户创建成功！
     */
    API_RESPONSE_USER_CREATED(20101, "用户创建成功！", HttpStatus.HTTP_CREATED),

    /**
     * 内容创建成功！
     */
    API_RESPONSE_CONTENT_CREATED(20102, "内容创建成功！", HttpStatus.HTTP_CREATED),

    // 权限成功 201XX
    /**
     * 退出成功！
     */
    API_RESPONSE_LOGOUT(20201, "退出成功！"),

    // 客户端过程中问题 40XXX
    /**
    * 请求异常！
    */
    API_RESPONSE_BAD_REQUEST(40000, "请求异常！", HttpStatus.HTTP_BAD_REQUEST),

    // 无法鉴权问题 401XX
    /**
     * 请先登录！
     */
    API_RESPONSE_UNAUTHORIZED(40100, "请先登录！", HttpStatus.HTTP_UNAUTHORIZED),

    // 缺少权限问题 403XX
    /**
     * 权限不足！
     */
    API_RESPONSE_ACCESS_DENIED(40300, "权限不足！", HttpStatus.HTTP_FORBIDDEN),
    /**
     * 当前用户已被锁定，请联系管理员解锁！
     */
    API_RESPONSE_USER_DISABLED(40301, "当前用户已被锁定，请联系管理员解锁！", HttpStatus.HTTP_FORBIDDEN),
    /**
     * 用户已存在！
     */
    API_RESPONSE_USER_EXISTED(40311, "用户已存在！", HttpStatus.HTTP_FORBIDDEN),
    /**
     * 内容已存在！
     */
    API_RESPONSE_CONTENT_EXISTED(40321, "类似内容已存在！请确认是否强制新建！", HttpStatus.HTTP_FORBIDDEN),

    // 请求内容错误 404XX
    /**
     * 请求不存在！
     */
    API_RESPONSE_REQUEST_NOT_FOUND(40400, "请求内容不存在！", HttpStatus.HTTP_NOT_FOUND),

    // 请求方式越权 405XX
    /**
     * 请求方式不支持！
     */
    API_RESPONSE_HTTP_BAD_METHOD(40500, "请求方式不支持！", HttpStatus.HTTP_BAD_METHOD),

    // 请求参数问题 406XX
    /**
     * 参数不符合要求！
     */
    API_RESPONSE_PARAM_BAD(40600, "参数不符合要求！", HttpStatus.HTTP_BAD_REQUEST),
    /**
     * 参数不能为空！
     */
    API_RESPONSE_PARAM_NOT_NULL(40601, "参数不能为空！", HttpStatus.HTTP_BAD_REQUEST),
    /**
     * 用户名或密码错误！
     */
    API_RESPONSE_USERNAME_PASSWORD_ERROR(40602, "用户名或密码错误！", HttpStatus.HTTP_BAD_REQUEST),


    // 服务器过程中问题 50XXX
    /**
     * 操作异常！
     */
    API_RESPONSE_ERROR(50000, "操作异常！", HttpStatus.HTTP_INTERNAL_ERROR),

    // 权限识别问题 501XX
    /**
     * token 已过期，请重新登录！
     */
    API_RESPONSE_TOKEN_EXPIRED(50102, "token 已过期，请重新登录！", HttpStatus.HTTP_INTERNAL_ERROR),
    /**
     * token 解析失败，请尝试重新登录！
     */
    API_RESPONSE_TOKEN_PARSE_ERROR(50103, "token 解析失败，请尝试重新登录！", HttpStatus.HTTP_INTERNAL_ERROR),

    // 权限操作问题 502XX
    /**
     * 当前用户已在别处登录，请尝试更改密码或重新登录！
     */
    API_RESPONSE_TOKEN_OUT_OF_CTRL(50201, "当前用户已在别处登录，请尝试更改密码或重新登录！", HttpStatus.HTTP_INTERNAL_ERROR),
    /**
     * 无法手动踢出自己，请尝试退出登录操作！
     */
    API_RESPONSE_KICKOUT_SELF(50201, "无法手动踢出自己，请尝试退出登录操作！", HttpStatus.HTTP_INTERNAL_ERROR),

    // 数据库过程问题 503XX
    API_RESPONSE_DATABASE_ERROR(50300,"数据库过程异常，请稍后重试！",HttpStatus.HTTP_INTERNAL_ERROR);

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 返回信息
     */
    private final String message;

    /**
     * http状态码
     **/
    private final Integer httpStatusCode;

    ApiStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = HttpStatus.HTTP_OK;
    }

    ApiStatus(Integer code, String message, Integer httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public static ApiStatus fromCode(Integer code) {
        ApiStatus[] statuses = ApiStatus.values();
        for (ApiStatus status : statuses) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return API_RESPONSE_SUCCESS;
    }

    @Override
    public String toString() {
        JSONObject resultJson = JSONUtil.createObj();
        resultJson
                .set("apiCode", this.getCode())
                .set("message", this.getMessage());
        return JSONUtil.toJsonStr(resultJson);
    }

}
