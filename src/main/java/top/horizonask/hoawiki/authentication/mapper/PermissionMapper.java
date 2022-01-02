package top.horizonask.hoawiki.authentication.mapper;

import top.horizonask.hoawiki.authentication.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select({"<script>",
            "SELECT DISTINCT " +
                    "permissions.* " +
                    "FROM permissions, roles_permission, roles " +
                    "WHERE " +
                    "roles.role_id = roles_permission.role_id " +
                    "AND " +
                    "permissions.permission_id = roles_permission.permission_id " +
                    "AND " +
                    "roles.role_id IN ",
            "<foreach collection = 'roleId' item = 'item' open = '(' separator = ',' close = ')'>",
            "   #{item}",
            "</foreach>",
            "</script>",
    }
    )
    List<Permission> findByRoleId(@Param("roleId") List<Long> roleId);
}
