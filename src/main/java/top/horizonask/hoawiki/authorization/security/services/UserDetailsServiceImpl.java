package top.horizonask.hoawiki.authorization.security.services;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.horizonask.hoawiki.authorization.entity.Role;
import top.horizonask.hoawiki.authorization.entity.User;
import top.horizonask.hoawiki.authorization.mapper.UserMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/29 22:47
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDetailsImpl loadUserByUserId(Long userId) throws UsernameNotFoundException{
        User user = userMapper.selectById(userId);
        return getUserDetails(user);
    }

    private UserDetailsImpl getUserDetails(User user) {
        if(user!=null){
            UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
            BeanUtils.copyProperties(user,userDetailsImpl);

            Set<GrantedAuthority> authorities = new HashSet<>();

            List<Role> roleList = userMapper.findRoleByUserId(userDetailsImpl.getUserId());
            roleList.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            });

            userDetailsImpl.setAuthorities(authorities);

            return userDetailsImpl;
        }
        return null;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userMapper.findByEmail(userEmail);
        return getUserDetails(user);
    }
}
