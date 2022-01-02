package top.horizonask.hoawiki.authentication.security.services;

import top.horizonask.hoawiki.authentication.entity.User;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @description: User details for spring boot security.
 * @author: Yanbo Han
 * @time: 2021/12/29 22:50
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDetailsImpl extends User implements UserDetails {

    private Collection<GrantedAuthority> authorities;

    private boolean isAccountNonExpired = false;

    private boolean isAccountNonLocked = false;

    private boolean isCredentialsNonExpired = false;

    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
