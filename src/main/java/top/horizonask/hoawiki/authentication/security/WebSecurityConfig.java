package top.horizonask.hoawiki.authentication.security;

import org.springframework.security.authentication.AuthenticationManager;
import top.horizonask.hoawiki.authentication.security.fliter.JWTAuthenticationFilter;
import top.horizonask.hoawiki.authentication.security.provider.UserAuthenticationProvider;
import top.horizonask.hoawiki.authentication.security.provider.UserPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import top.horizonask.hoawiki.authentication.security.handler.*;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsServiceImpl;

/**
 * @description: Configuration of spring boot security
 * @author: Yanbo Han
 * @time: 2021/12/29 22:45
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserAccessDeniedHandler userAccessDeniedHandler;

    private UserNotLoginHandler userNotLoginHandler;

    private UserAuthenticationProvider userAuthenticationProvider;

    private UserPermissionEvaluator userPermissionEvaluator;

    private UserDetailsServiceImpl userDetailsServiceImpl;

    public WebSecurityConfig(UserAccessDeniedHandler userAccessDeniedHandler, UserNotLoginHandler userNotLoginHandler, UserAuthenticationProvider userAuthenticationProvider, UserPermissionEvaluator userPermissionEvaluator, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userAccessDeniedHandler = userAccessDeniedHandler;
        this.userNotLoginHandler = userNotLoginHandler;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.userPermissionEvaluator = userPermissionEvaluator;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler).authenticationEntryPoint(userNotLoginHandler)
                .and().cors()
                .and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), userDetailsServiceImpl));
    }
}
