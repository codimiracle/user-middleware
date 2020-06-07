package com.codimiracle.web.middleware.user.adapter.apache.shiro;

import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.service.UserService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceRealmAdapterTest {
    private UserServiceRealmAdapter adapter;
    @MockBean
    private PrincipalCollection collection;
    @Resource
    private UserService userService;

    @BeforeEach
    void setUp() {
        adapter = new UserServiceRealmAdapter(userService);
    }

    void doGetAuthorizationInfo() {
        final String username = "codimiracle";
        UserMetadata metadata = new UserMetadata();
        List<Role> roleList = new ArrayList<>();
        Role role = new Role();
        role.setId("1020");
        role.setIdentifier("role_a");
        role.setAuthorities("authority:test");
        roleList.add(role);
        metadata.setRoleList(roleList);
        metadata.setUsername(username);
        userService.save(metadata);
        when(collection.getPrimaryPrincipal()).thenReturn(username);
        AuthorizationInfo authorizationInfo = adapter.doGetAuthorizationInfo(collection);
        assertTrue(authorizationInfo.getRoles().contains(role.getIdentifier()));
        assertTrue(authorizationInfo.getStringPermissions().contains(role.getAuthorities()));
        verify(collection);
    }

    @Test
    void doGetAuthenticationInfo() {
    }
}