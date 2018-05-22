package pl.jakub.fisz.security;

import com.google.common.collect.ImmutableSet;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class OneUserDetails implements UserDetails {

    @Getter
    @Value("${username}")
    private String username;

    @Getter
    @Value("${password}")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ImmutableSet.of(() -> "ADMIN");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
