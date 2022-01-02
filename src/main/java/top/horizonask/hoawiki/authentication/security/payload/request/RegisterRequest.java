package top.horizonask.hoawiki.authentication.security.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/2 18:08
 */

public class RegisterRequest {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotBlank
    @Size(max=50)
    @Email
    private String userEmail;

    @NotBlank
    @Size(min=8,max=40)
    private String password;

    @NotBlank
    @Size(max=50)
    private String userName;
}
