package top.horizonask.hoawiki.authorization.controller.info;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.horizonask.hoawiki.authorization.entity.Permission;
import top.horizonask.hoawiki.authorization.mapper.PermissionMapper;
import top.horizonask.hoawiki.common.ResponseUtils;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/30 18:34
 */

@Slf4j
@RestController
@RequestMapping(value ="/auth/user/info")
public class PermissionController {
    public PermissionMapper permissionMapper;

    public PermissionController(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @PreAuthorize(value="hasRole('Admin') or hasPermission('/permission/role','admin:rolesPermission')")
    @RequestMapping("/permission/role/{roleId}")
    public ResponseUtils permissionId(@PathVariable Long roleId) throws RoleNotFoundException {
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        List<Permission> permission = permissionMapper.findByRoleId(roleIds);
        if(permission == null){
            throw new RoleNotFoundException();
        }
        ResponseUtils res = ResponseUtils.success().message("Get user information");
        for (Permission
                onePermission :
                permission
        ) {
            res.accumulate("permissionItems", onePermission.getJson());
        }
        return res;
    }
}
