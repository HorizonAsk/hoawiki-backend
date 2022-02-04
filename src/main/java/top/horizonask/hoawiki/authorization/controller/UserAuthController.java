package top.horizonask.hoawiki.authorization.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.horizonask.hoawiki.authorization.security.JWTTokenUtil;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsImpl;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsServiceImpl;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.authorization.request.LoginRequest;

import javax.validation.Valid;

/**
 * @description: Spring-boot Controller of user authorization
 * @author: Yanbo Han
 * @time: 2022/1/2 17:23
 */
@Slf4j
@RestController
@RequestMapping("/auth/user")
public class UserAuthController {
    final
    AuthenticationManager authenticationManager;

    UserDetailsServiceImpl userDetailsServiceImpl;

    public UserAuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @PostMapping("/login")
    public ResponseEntity<JSONObject> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult validResult) {
        if (validResult.hasErrors()) {
            ResponseUtils responseUtils = ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD);
            for (FieldError error : validResult.getFieldErrors()) {
                responseUtils.accumulate("error", JSONUtil.createObj().set(error.getField(), error.getDefaultMessage()));
            }
            return responseUtils.toResponseEntity();
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetailsImpl = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUserEmail());
            String jwt = JWTTokenUtil.createAccessToken(userDetailsImpl);

            return ResponseUtils.success().data("jwt", jwt).toResponseEntity();
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_USERNAME_PASSWORD_ERROR).toResponseEntity();
        } catch (Exception e) {
            log.warn("Wrong in login: " + loginRequest.toString());
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_ERROR).toResponseEntity();
        }
    }
}
