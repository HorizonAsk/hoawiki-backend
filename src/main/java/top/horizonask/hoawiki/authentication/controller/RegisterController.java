package top.horizonask.hoawiki.authentication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.horizonask.hoawiki.authentication.common.ResponseUtils;
import top.horizonask.hoawiki.authentication.entity.User;
import top.horizonask.hoawiki.authentication.entity.UsersRole;
import top.horizonask.hoawiki.authentication.mapper.UserMapper;
import top.horizonask.hoawiki.authentication.mapper.UsersRoleMapper;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/1 14:07
 */

@Slf4j
@RestController
@RequestMapping(value ="/register")
public class RegisterController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserMapper userMapper;

    private final UsersRoleMapper usersRoleMapper;

    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, UsersRoleMapper usersRoleMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.usersRoleMapper = usersRoleMapper;
    }

    @RequestMapping("/user")
    public ResponseUtils user(String username, String Email, String password){
        User user = new User();
        user.setEmail(Email);
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        log.info(username);
        log.info(Email);
        log.info(password);
        userMapper.insert(user);

        UsersRole usersRole = new UsersRole();
        usersRole.setUserId(user.getUserId());
        usersRole.setRoleId(2L); // TODO: make ordinary user registration more elegant.
        usersRoleMapper.insert(usersRole);

        return ResponseUtils.success(user.getJson());
    }
}
