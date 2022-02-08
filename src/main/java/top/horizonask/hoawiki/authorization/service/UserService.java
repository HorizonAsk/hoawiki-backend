package top.horizonask.hoawiki.authorization.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import top.horizonask.hoawiki.authorization.entity.User;

public interface UserService extends IService<User> {

    /**
     * Get user's information for public.
     * @param userId id of user to get
     * @return cn.hutool.json.JSONObject
     */
    JSONObject getUserBriefInfo(Long userId);

    /**
     * <p>Get user's information <b>ONLY</b> for him and administrators.</p>
     * @param userId id of user to get
     * @return cn.hutool.json.JSONObject
     */
    JSONObject getUserPrivateInfo(Long userId);

    /**
     * <p>Get user's information <b>ONLY</b> for administrators.</p>
     * @param userId id of user to get
     * @return cn.hutool.json.JSONObject
     */
    JSONObject getUserAdministrationInfo(Long userId);
}
