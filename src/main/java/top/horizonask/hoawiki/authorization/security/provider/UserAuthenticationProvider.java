package top.horizonask.hoawiki.authorization.security.provider;

import top.horizonask.hoawiki.authorization.security.services.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsServiceImpl;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 3:27
 */

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public UserAuthenticationProvider(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();


        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(userEmail);

        if (userDetailsImpl==null){
            throw new UsernameNotFoundException("User not exist.");
        }
        if (!new BCryptPasswordEncoder().matches(password,userDetailsImpl.getPassword())){
            throw new BadCredentialsException("Wrong username or password!");
        }

        if (!userDetailsImpl.available()){
            throw new LockedException("User has been deleted.");
        }
        return new UsernamePasswordAuthenticationToken(userDetailsImpl,password,userDetailsImpl.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
