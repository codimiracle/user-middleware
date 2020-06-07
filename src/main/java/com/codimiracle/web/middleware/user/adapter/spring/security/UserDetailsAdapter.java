package com.codimiracle.web.middleware.user.adapter.spring.security;

import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.google.common.base.Splitter;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Spring security {@link UserDetails} abstract adapter, wrapping {@link UserMetadata}
 * and using {@link Role} to satisfy the specification of {@link UserDetails}
 *
 * @author Codimiracle
 */
@Slf4j
public class UserDetailsAdapter extends UserMetadata implements UserDetails {
    @Delegate
    private final UserMetadata userMetadata;

    public UserDetailsAdapter(UserMetadata userMetadata) {
        Objects.requireNonNull(userMetadata, "Can not use null for adapting user metadata.");
        this.userMetadata = userMetadata;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UserMetadata) {
            return userMetadata.equals(o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return userMetadata.hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roleList = userMetadata.getRoleList();
        if (Objects.nonNull(roleList) && roleList.isEmpty()) {
            log.warn("Be careful, the property of role list is empty that will use empty list");
            return Collections.emptyList();
        }
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleList.forEach((role) -> {
            authorities.addAll(Splitter.on(",")
                    .omitEmptyStrings()
                    .splitToList(role.getAuthorities())
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));
        });
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (Objects.isNull(this.getExpiredAt())) {
            return true;
        }
        return this.getExpiredAt().getTime() > System.currentTimeMillis();
    }

    @Override
    public boolean isAccountNonLocked() {
        if (Objects.isNull(this.getLocked())) {
            return true;
        }
        return !this.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (Objects.isNull(userMetadata.getCredentialsExpiredAt())) {
            return true;
        }
        return userMetadata.getCredentialsExpiredAt().getTime() > System.currentTimeMillis();
    }

    @Override
    public boolean isEnabled() {
        if (Objects.isNull(this.getEnabled())) {
            return true;
        }
        return this.getEnabled();
    }
}
