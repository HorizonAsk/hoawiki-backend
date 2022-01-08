package top.horizonask.hoawiki.common.request;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/2 18:04
 */

public class LoginRequest {
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    private String userEmail;

    @NotBlank
    private String password;


}
