package top.horizonask.hoawiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.horizonask.hoawiki.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
