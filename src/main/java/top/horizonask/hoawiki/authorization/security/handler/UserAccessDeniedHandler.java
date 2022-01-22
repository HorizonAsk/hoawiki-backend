package top.horizonask.hoawiki.authorization.security.handler;

import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 2:36
 */

@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.responseJson(response,ResponseUtils.response(false,ApiStatus.API_RESPONSE_ACCESS_DENIED,accessDeniedException.getMessage()));
    }
}
