package top.horizonask.hoawiki.authorization.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/2 18:08
 */
@Data
public class RegisterRequest {
    @NotBlank(message = "电子邮件地址不能为空")
    @Size(max = 200, message = "电子邮件地址过长")
    @Email(message = "电子邮件地址格式错误")
    private String userEmail;

    @NotBlank
    @Size(min = 8, max = 40, message = "密码长度不合适")
    private String password;

    @NotBlank(message = "用户昵称不能为空")
    @Size(max = 50, message = "用户昵称过长")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]*$", message = "用户昵称只能包含文字、字母和数字")
    private String userName;
}
