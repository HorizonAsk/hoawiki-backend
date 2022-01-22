package top.horizonask.hoawiki.authorization.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.horizonask.hoawiki.common.ResponseUtils;
import top.horizonask.hoawiki.common.request.RegisterRequest;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.entity.UsersRole;
import top.horizonask.hoawiki.authorization.mapper.UserMapper;
import top.horizonask.hoawiki.authorization.mapper.UsersRoleMapper;

import javax.validation.Valid;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/1 14:07
 */

@Slf4j
@RestController
@RequestMapping(value ="/auth/user")
public class RegisterController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserMapper userMapper;

    private final UsersRoleMapper usersRoleMapper;

    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, UsersRoleMapper usersRoleMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.usersRoleMapper = usersRoleMapper;
    }

    @RequestMapping("/register")
    public ResponseUtils user(@Valid @RequestBody RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getUserEmail());
        user.setUsername(registerRequest.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        userMapper.insert(user);

        UsersRole usersRole = new UsersRole();
        usersRole.setUserId(user.getUserId());
        usersRole.setRoleId(2L); // TODO: make ordinary user registration more elegant.
        usersRoleMapper.insert(usersRole);

        return ResponseUtils.success(user.getJson());
    }
}
