package top.horizonask.hoawiki.authorization.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.authorization.entity.Permission;
import top.horizonask.hoawiki.authorization.entity.Role;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.mapper.PermissionMapper;
import top.horizonask.hoawiki.authorization.mapper.RoleMapper;
import top.horizonask.hoawiki.authorization.mapper.UserMapper;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsImpl;
import top.horizonask.hoawiki.common.ApiStatus;
import top.horizonask.hoawiki.common.ResponseUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/auth/user")
public class UserInfoController {

    UserMapper userMapper;

    RoleMapper roleMapper;

    PermissionMapper permissionMapper;

    public UserInfoController(UserMapper userMapper, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    @RequestMapping("/{id}")
    public ResponseUtils userSelfInfoApi(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_REQUEST_NOT_FOUND).message("User not exist.");
        }
        ResponseUtils res = ResponseUtils.success().message("Get user information with roles");
        res.data("userItems", user.getJson());

        List<Role> roles = roleMapper.findByUserId(user.getUserId());
        for (Role role : roles) {
            res.accumulate("roleItems", role.getJson());
        }
        return res;
    }

    @GetMapping("/info/permission")
    public ResponseUtils userSelfPermissionInfoApi() {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userMapper.selectById(userDetailsImpl.getUserId());
        if (user == null) {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_REQUEST_NOT_FOUND).message("User not exist.");
        }
        ResponseUtils res = ResponseUtils.success().message("Get user information with roles and permissions");
        res.data("userItems", user.getJson());

        List<Role> roles = roleMapper.findByUserId(user.getUserId());
        List<Long> roleIds = new ArrayList<>();
        for (Role role : roles) {
            res.accumulate("roleItems", role.getJson());
            roleIds.add(role.getRoleId());
        }

        List<Permission> permissionList = permissionMapper.findByRoleId(roleIds);
        for (Permission permission : permissionList) {
            res.accumulate("permissionItems", permission.getJson());
        }
        return res;
    }

    @PreAuthorize(value="hasRole('user')")
    @RequestMapping("/search/{username}")
    public ResponseUtils userSearchApi(@PathVariable String username) {
        List<User> userList = userMapper.findByUsername(username);
        if (userList.size() == 0) {
            return ResponseUtils.fail(ApiStatus.API_RESPONSE_REQUEST_NOT_FOUND).message("User not exist.");
        }
        ResponseUtils res = ResponseUtils.success().message("Get user information");
        for (User user : userList) {
            res.accumulate("userItems", user.getJson());
        }
        return res;
    }
}
