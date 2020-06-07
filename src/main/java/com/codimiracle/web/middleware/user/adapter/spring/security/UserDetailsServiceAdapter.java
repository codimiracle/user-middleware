package com.codimiracle.web.middleware.user.adapter.spring.security;

import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * bridge between spring security {@link UserDetailsService} and {@link UserService}
 *
 * @author Codimiracle
 */
public class UserDetailsServiceAdapter implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceAdapter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserMetadata userMetadata = userService.findByUsername(s);
        if (Objects.nonNull(userMetadata)) {
            return new UserDetailsAdapter(userMetadata);
        }
        throw new UsernameNotFoundException("username [" + s + "] not found.");
    }
}
