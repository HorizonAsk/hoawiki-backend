package top.horizonask.hoawiki.common.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/2 18:04
 */
@Data
public class LoginRequest {
    @NotBlank
    @Size(max = 200, message = "电子邮件地址过长")
    @Email(message = "电子邮件地址格式错误")
    private String userEmail;

    @NotBlank
    private String password;


}
