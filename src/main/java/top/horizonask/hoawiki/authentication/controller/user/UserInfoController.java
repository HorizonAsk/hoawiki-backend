package top.horizonask.hoawiki.authentication.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.common.ApiResponse;
import top.horizonask.hoawiki.authentication.entity.Permission;
import top.horizonask.hoawiki.authentication.entity.Role;
import top.horizonask.hoawiki.authentication.entity.User;
import top.horizonask.hoawiki.authentication.mapper.PermissionMapper;
import top.horizonask.hoawiki.authentication.mapper.RoleMapper;
import top.horizonask.hoawiki.authentication.mapper.UserMapper;
import top.horizonask.hoawiki.authentication.security.services.UserDetailsImpl;

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
    public ApiResponse userSelfInfoApi(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return ApiResponse.error().message("User not exist.");
        }
        ApiResponse res = ApiResponse.ok().message("Get user information with roles");
        res.data("userItems", user.getJson());

        List<Role> roles = roleMapper.findByUserId(user.getUserId());
        for (Role role : roles) {
            res.accumulate("roleItems", role.getJson());
        }
        return res;
    }

    @GetMapping("/info/permission")
    public ApiResponse userSelfPermissionInfoApi() {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userMapper.selectById(userDetailsImpl.getUserId());
        if (user == null) {
            return ApiResponse.error().message("User not exist.");
        }
        ApiResponse res = ApiResponse.ok().message("Get user information with roles and permissions");
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
    public ApiResponse userSearchApi(@PathVariable String username) {
        List<User> userList = userMapper.findByUsername(username);
        if (userList.size() == 0) {
            return ApiResponse.error().message("User not exist.");
        }
        ApiResponse res = ApiResponse.ok().message("Get user information");
        for (User user : userList) {
            res.accumulate("userItems", user.getJson());
        }
        return res;
    }
}
