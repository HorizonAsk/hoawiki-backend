package top.horizonask.hoawiki.authorization.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 2:53
 */

@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils.responseJson(response, ResponseUtils.response(false,ApiStatus.API_RESPONSE_UNAUTHORIZED, authException.getMessage()));
    }
}
