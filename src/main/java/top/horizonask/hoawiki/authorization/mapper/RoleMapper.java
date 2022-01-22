package top.horizonask.hoawiki.authorization.mapper;

import top.horizonask.hoawiki.authorization.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT * FROM roles where role_name=#{roleName}")
    Role findByRoleName(@Param("roleName") String roleName);

    @Select("SELECT DISTINCT " +
            "roles.* " +
            "FROM roles, users_role, users " +
            "WHERE " +
            "users.user_id = users_role.user_id " +
            "AND " +
            "users_role.role_id = users_role.role_id " +
            "AND " +
            "users.user_id = #{userId}")
    List<Role> findByUserId(@Param("userId") Long userId);
}
