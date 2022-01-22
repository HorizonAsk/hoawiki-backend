package top.horizonask.hoawiki.authorization.security.provider;

import top.horizonask.hoawiki.authorization.mapper.UserMapper;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import top.horizonask.hoawiki.authorization.entity.Permission;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsImpl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2022/1/1 0:52
 */

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    private final UserMapper userMapper;

    public UserPermissionEvaluator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        if (userDetailsImpl.getIsSuperAdmin()){
            return true;
        }

        Set<String> permissions = new HashSet<String>();

        List<Permission> permissionList = userMapper.findPermissionByUserId(userDetailsImpl.getUserId());

        permissionList.forEach(permissionOne->{
            permissions.add(permissionOne.getPermissionName());
        });

        return permissions.contains(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
