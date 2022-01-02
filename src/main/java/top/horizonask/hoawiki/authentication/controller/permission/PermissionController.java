package top.horizonask.hoawiki.authentication.controller.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.authentication.common.ApiResponse;
import top.horizonask.hoawiki.authentication.entity.Permission;
import top.horizonask.hoawiki.authentication.mapper.PermissionMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/30 18:34
 */

@Slf4j
@RestController
public class PermissionController {
    public PermissionMapper permissionMapper;

    public PermissionController(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @PreAuthorize(value="hasRole('Admin') or hasPermission('/permission/role','admin:rolesPermission')")
    @RequestMapping("/permission/role/{roleId}")
    public ApiResponse permissionId(@PathVariable Long roleId) {
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        List<Permission> permission = permissionMapper.findByRoleId(roleIds);
        ApiResponse res = ApiResponse.ok().message("Get user information");
        for (Permission
                onePermission :
                permission
        ) {
            res.accumulate("permissionItems", onePermission.getJson());
        }
        return res;
    }
}
