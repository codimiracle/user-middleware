package com.codimiracle.web.middleware.user.adapter.spring.security;

import com.codimiracle.web.middleware.user.adapter.spring.security.UserDetailsAdapter;
import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SpringSecurityUserDetailsAdapterTest {
    private static final int DELAY = 10000;
    private UserDetailsAdapter adapter;
    private UserMetadata metadata;

    @BeforeEach
    void setUp() {
        this.metadata = new UserMetadata();
        List<Role> roleList = new ArrayList<>();
        Role role = new Role();
        role.setAuthorities("test:test");
        role.setDescription("test");
        role.setName("test");
        roleList.add(role);
        this.metadata.setRoleList(roleList);
        this.adapter = new UserDetailsAdapter(metadata);
    }

    @AfterEach
    void tearDown() {
        this.metadata = null;
        this.adapter = null;
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = this.adapter.getAuthorities();
        assertFalse(authorities.isEmpty());
        for (GrantedAuthority authority : authorities) {
            assertEquals("test:test", authority.getAuthority());
        }
    }

    @Test
    void isAccountNonExpired() {
        metadata.setExpiredAt(new Date(System.currentTimeMillis() + DELAY));
        assertTrue(adapter.isAccountNonExpired());
    }

    @Test
    void isAccountNonExpiredForNull() {
        assertTrue(adapter.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        metadata.setExpiredAt(new Date(System.currentTimeMillis() + DELAY));
        assertTrue(adapter.isAccountNonLocked());
    }
    @Test
    void isAccountNonLockedForNull() {
        assertTrue(adapter.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        metadata.setCredentialsExpiredAt(new Date(System.currentTimeMillis() + DELAY));
        assertTrue(adapter.isCredentialsNonExpired());
    }
    @Test
    void isCredentialsNonExpiredForNull() {
        assertTrue(adapter.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        metadata.setEnabled(true);
        assertTrue(adapter.isEnabled());
    }
    @Test
    void isEnabledForNull() {
        assertTrue(adapter.isEnabled());
    }
}