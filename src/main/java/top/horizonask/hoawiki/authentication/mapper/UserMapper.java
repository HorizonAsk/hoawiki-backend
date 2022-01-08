package top.horizonask.hoawiki.authentication.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.horizonask.hoawiki.authentication.entity.Permission;
import top.horizonask.hoawiki.authentication.entity.Role;
import top.horizonask.hoawiki.authentication.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM users where username=#{username}")
    List<User> findByUsername(@Param("username") String username);

    @Select("SELECT * FROM users where email=#{userEmail}")
    User findByEmail(@Param("userEmail") String userEmail);

    @Select("SELECT DISTINCT " +
            "roles.* " +
            "FROM roles, users_role, users " +
            "WHERE " +
            "users.user_id = users_role.user_id " +
            "AND " +
            "users_role.role_id = users_role.role_id " +
            "AND " +
            "users.user_id = #{userId}")
    List<Role> findRoleByUserId(Long userId);

    @Select("SELECT DISTINCT permissions.* FROM permissions, roles_permission, roles " +
            "WHERE roles.role_id = roles_permission.role_id " +
            "AND permissions.permission_id = roles_permission.permission_id " +
            "AND roles.role_id " +
            "IN (" +
            "SELECT roles.role_id " +
            "FROM roles, users_role,users " +
            "WHERE roles.role_id = users_role.role_id " +
            "AND users.user_id = users_role.user_id " +
            "AND users.user_id = #{userId}" +
            ")"
    )
    List<Permission> findPermissionByUserId(Long userId);
}
