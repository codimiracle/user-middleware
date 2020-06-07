package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.pojo.vo.SocialUserVO;
import com.codimiracle.web.middleware.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    void save() {
        UserMetadata metadata = new UserMetadata();
        List<Role> roleList = new ArrayList<>();
        Role role = new Role();
        role.setAuthorities("test:hello");
        roleList.add(role);
        metadata.setRoleList(roleList);
        metadata.setUsername("testSave");
        userService.save(metadata);
        UserMetadata result = userService.findByUsername(metadata.getUsername());
        assertEquals(metadata.getUsername(), result.getUsername());
        assertNotNull(metadata.getCreatedAt());
        assertNotNull(metadata.getUpdatedAt());

    }

    @Test
    void findByUsername() {
        UserMetadata metadata = new UserMetadata();
        metadata.setUsername("codimiracle");
        userService.save(metadata);
        UserMetadata result = userService.findByUsername(metadata.getUsername());
        assertEquals(metadata.getUsername(), result.getUsername());
    }

    @Test
    void findSocialUserById() {
        UserMetadata metadata = new UserMetadata();
        metadata.setUsername("cdmrc");
        userService.save(metadata);
        SocialUserVO socialUser = userService.findSocialUserById(metadata.getId());
        assertEquals(metadata.getUsername(), socialUser.getUsername());
    }
}