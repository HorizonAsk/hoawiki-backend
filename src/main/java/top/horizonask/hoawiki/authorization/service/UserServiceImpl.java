package top.horizonask.hoawiki.authorization.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.mapper.UserMapper;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 0:18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    final
    UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Get user's information for public.
     *
     * @param userId id of user to get
     * @return cn.hutool.json.JSONObject
     */
    @Override
    public JSONObject getUserBriefInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if(user!=null){
            return user.getJsonPublic();
        }else{
            return null;
        }
    }

    /**
     * <p>Get user's information <b>ONLY</b> for him and administrators.</p>
     *
     * @param userId id of user to get
     * @return cn.hutool.json.JSONObject
     */
    @Override
    public JSONObject getUserPrivateInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if(user!=null){
            return user.getJson();
        }else{
            return null;
        }
    }

    /**
     * <p>Get user's information <b>ONLY</b> for administrators.</p>
     *
     * @param userId id of user to get
     * @return cn.hutool.json.JSONObject
     */
    @Override
    public JSONObject getUserAdministrationInfo(Long userId) {
        User user = userMapper.selectById(userId);
        if(user!=null){
            return user.getJson();
        }else{
            return null;
        }
    }
}
