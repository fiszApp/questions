package pl.jakub.fisz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OneUserDetailsService implements UserDetailsService {

    @Autowired
    private OneUserDetails oneUserDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Objects.equals(username, oneUserDetails.getUsername())) {
            return oneUserDetails;
        }
        throw new UsernameNotFoundException(String.format("User %s not exists!", username));
    }
}
