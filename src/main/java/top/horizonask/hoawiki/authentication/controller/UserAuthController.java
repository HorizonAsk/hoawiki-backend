package top.horizonask.hoawiki.authentication.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.horizonask.hoawiki.authentication.mapper.RoleMapper;
import top.horizonask.hoawiki.authentication.mapper.UserMapper;
import top.horizonask.hoawiki.authentication.security.JWTTokenUtil;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsImpl;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsServiceImpl;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.common.request.LoginRequest;

import javax.validation.Valid;

/**
 * @description: Spring-boot Controller of user authentication
 * @author: Yanbo Han
 * @time: 2022/1/2 17:23
 */

@RestController
@RequestMapping("/auth/user")
public class UserAuthController {
    final
    AuthenticationManager authenticationManager;

    UserDetailsServiceImpl userDetailsServiceImpl;

    public UserAuthController(AuthenticationManager authenticationManager, UserMapper userMapper, UserDetailsServiceImpl userDetailsServiceImpl, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @PostMapping("/login")
    public ResponseUtils authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetailsImpl = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUserEmail());
        String jwt = JWTTokenUtil.createAccessToken(userDetailsImpl);

        return ResponseUtils.success(jwt);
    }


}
