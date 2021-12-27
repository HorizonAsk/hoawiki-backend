package top.horizonask.hoawiki.controller.user;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.*;

import top.horizonask.hoawiki.entity.User;
import top.horizonask.hoawiki.mapper.UserMapper;

@RestController
public class UserController {

    final
    UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/user/{id}")
    public JSONObject userInfoApi(@PathVariable String id){
        JSONObject userInfoJson = JSONUtil.createObj();
        User user=userMapper.selectById(id);
        userInfoJson.set("user_id",user.getId());
        userInfoJson.set("username",user.getUsername());
        userInfoJson.set("email",user.getEmail());
        userInfoJson.set("resultCode",200);
        return userInfoJson;
    }

    @PostMapping("/user/register")
    public JSONObject userRegisterApi(@RequestBody User user){
        userMapper.insert(user);
        JSONObject userRegisterResponce = JSONUtil.createObj();
        userRegisterResponce.set("user",user);
        userRegisterResponce.set("resultCode",200);
        return userRegisterResponce;
    }
}
