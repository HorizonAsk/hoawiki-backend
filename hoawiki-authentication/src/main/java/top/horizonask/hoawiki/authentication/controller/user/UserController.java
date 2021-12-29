package top.horizonask.hoawiki.authentication.controller.user;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.api.common.ApiResponse;
import top.horizonask.hoawiki.authentication.entity.User;
import top.horizonask.hoawiki.authentication.mapper.UserMapper;

import java.util.List;

@RestController
public class UserController {

    final
    UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/user/{id}")
    public ApiResponse userInfoApi(@PathVariable String id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return ApiResponse.error().message("User not exist.");
        }
        return ApiResponse.ok()
                .data(user.getJson())
                .message("Get user information");
    }

    @RequestMapping("/user/name/{username}")
    public ApiResponse userSearchApi(@PathVariable String username) {
        List<User> userList = userMapper.findByUsername(username);
        if (userList.size() == 0) {
            return ApiResponse.error().message("User not exist.");
        }
        ApiResponse res = ApiResponse.ok().message("Get user information");
        for (User user :
                userList) {
            res.accumulate("userItem", user.getJson());
        }
        return res;
    }

    @PostMapping("/user/register")
    public JSONObject userRegisterApi(@RequestBody User user) {
        userMapper.insert(user);
        JSONObject userRegisterResponse = JSONUtil.createObj();
        userRegisterResponse.set("user", user);
        userRegisterResponse.set("resultCode", 200);
        return userRegisterResponse;
    }
}
