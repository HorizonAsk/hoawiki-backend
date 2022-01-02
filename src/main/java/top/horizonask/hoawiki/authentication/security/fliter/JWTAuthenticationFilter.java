package top.horizonask.hoawiki.authentication.security.fliter;

import top.horizonask.hoawiki.authentication.security.JWTConfig;
import top.horizonask.hoawiki.authentication.security.JWTTokenUtil;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/1 3:49
 */

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsServiceImpl) {
        super(authenticationManager);
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader(JWTConfig.tokenHeader);

        if (token != null && token.startsWith(JWTConfig.tokenPrefix)) {
            Long userId = JWTTokenUtil.getUserIdFromJwtToken(token);

            UserDetailsImpl userDetailsImpl = userDetailsServiceImpl.loadUserByUserId(userId);

            if (userDetailsImpl != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, userDetailsImpl.getUserId(), userDetailsImpl.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
