package top.horizonask.hoawiki.authentication.common;

import lombok.Getter;

@Getter
public enum ApiStatus implements IStatus {
    /**
     * 操作成功！
     */
    API_RESPONSE_SUCCESS(10200, "操作成功！"),

    /**
     * 操作异常！
     */
    API_RESPONSE_ERROR(10500, "操作异常！"),

    /**
     * 退出成功！
     */
    API_RESPONSE_LOGOUT(10200, "退出成功！"),

    /**
     * 请先登录！
     */
    API_RESPONSE_UNAUTHORIZED(10401, "请先登录！"),

    /**
     * 暂无权限访问！
     */
    API_RESPONSE_ACCESS_DENIED(10403, "权限不足！"),

    /**
     * 请求不存在！
     */
    API_RESPONSE_REQUEST_NOT_FOUND(10404, "请求不存在！"),

    /**
     * 请求方式不支持！
     */
    API_RESPONSE_HTTP_BAD_METHOD(10405, "请求方式不支持！"),

    /**
     * 请求异常！
     */
    API_RESPONSE_BAD_REQUEST(10400, "请求异常！"),

    /**
     * 参数不匹配！
     */
    API_RESPONSE_PARAM_NOT_MATCH(10400, "参数不匹配！"),

    /**
     * 参数不能为空！
     */
    API_RESPONSE_PARAM_NOT_NULL(10400, "参数不能为空！"),

    /**
     * 当前用户已被锁定，请联系管理员解锁！
     */
    API_RESPONSE_USER_DISABLED(10403, "当前用户已被锁定，请联系管理员解锁！"),

    /**
     * 用户名或密码错误！
     */
    API_RESPONSE_USERNAME_PASSWORD_ERROR(15001, "用户名或密码错误！"),

    /**
     * token 已过期，请重新登录！
     */
    API_RESPONSE_TOKEN_EXPIRED(15002, "token 已过期，请重新登录！"),

    /**
     * token 解析失败，请尝试重新登录！
     */
    API_RESPONSE_TOKEN_PARSE_ERROR(15002, "token 解析失败，请尝试重新登录！"),

    /**
     * 当前用户已在别处登录，请尝试更改密码或重新登录！
     */
    API_RESPONSE_TOKEN_OUT_OF_CTRL(15003, "当前用户已在别处登录，请尝试更改密码或重新登录！"),

    /**
     * 无法手动踢出自己，请尝试退出登录操作！
     */
    API_RESPONSE_KICKOUT_SELF(15004, "无法手动踢出自己，请尝试退出登录操作！");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    ApiStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
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
        return String.format(" ApiStatus:{code=%s, message=%s} ", getCode(), getMessage());
    }

}
