package top.horizonask.hoawiki.authorization.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.entity.UsersRole;
import top.horizonask.hoawiki.authorization.mapper.UserMapper;
import top.horizonask.hoawiki.authorization.mapper.UsersRoleMapper;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.authorization.request.RegisterRequest;

import javax.validation.Valid;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/1 14:07
 */

@Slf4j
@RestController
@RequestMapping(value = "/auth/user")
public class RegisterController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserMapper userMapper;

    private final UsersRoleMapper usersRoleMapper;

    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, UsersRoleMapper usersRoleMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.usersRoleMapper = usersRoleMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<JSONObject> user(@Valid @RequestBody RegisterRequest registerRequest, BindingResult validResult) {
        if (validResult.hasErrors()) {
            ResponseUtils responseUtils = ResponseUtils.fail(ApiStatus.API_RESPONSE_PARAM_BAD);
            for (FieldError error : validResult.getFieldErrors()) {
                responseUtils.accumulate("error", JSONUtil.createObj().set(error.getField(), error.getDefaultMessage()));
            }
            return responseUtils.toResponseEntity();
        }
        String email = registerRequest.getUserEmail();
        String username = registerRequest.getUserName();
        if (userMapper.findByEmail(email) == null) {
            User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
            userMapper.insert(user);

            UsersRole usersRole = new UsersRole();
            usersRole.setUserId(user.getUserId());
            usersRole.setRoleId(2L); // TODO: make ordinary user registration more elegant.
            usersRoleMapper.insert(usersRole);

            return ResponseUtils.success(ApiStatus.API_RESPONSE_USER_CREATED, user.getJson()).toResponseEntity();
        } else {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_USER_EXISTED).toResponseEntity();
        }
    }
}
