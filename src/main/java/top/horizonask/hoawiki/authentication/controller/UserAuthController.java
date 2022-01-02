package top.horizonask.hoawiki.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.authentication.common.ResponseUtils;
import top.horizonask.hoawiki.authentication.mapper.RoleMapper;
import top.horizonask.hoawiki.authentication.mapper.UserMapper;
import top.horizonask.hoawiki.authentication.security.JWTConfig;
import top.horizonask.hoawiki.authentication.security.JWTTokenUtil;
import top.horizonask.hoawiki.authentication.security.payload.request.LoginRequest;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsImpl;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsServiceImpl;

import javax.validation.Valid;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/2 17:23
 */

@RestController
@RequestMapping()
public class UserAuthController {
    final
    AuthenticationManager authenticationManager;

    final
    UserMapper userMapper;

    UserDetailsServiceImpl userDetailsServiceImpl;

    final
    RoleMapper roleMapper;

    public UserAuthController(AuthenticationManager authenticationManager, UserMapper userMapper, UserDetailsServiceImpl userDetailsServiceImpl, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    final
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseUtils authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(),loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetailsImpl = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUserEmail());
        String jwt = JWTTokenUtil.createAccessToken(userDetailsImpl);

        return ResponseUtils.success(jwt);
    }


}
