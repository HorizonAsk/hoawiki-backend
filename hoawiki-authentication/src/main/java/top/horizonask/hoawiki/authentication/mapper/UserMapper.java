package top.horizonask.hoawiki.authentication.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.horizonask.hoawiki.authentication.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM users where username=#{username}")
    List<User> findByUsername(@Param("username") String username);
}
