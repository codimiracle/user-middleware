package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.pojo.po.Authority;
import com.codimiracle.web.middleware.user.service.AuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorityServiceImplTest {
    @Resource
    private AuthorityService authorityService;

    @Test
    void findByIds() {
        List<String> ids = new ArrayList<>();
        List<Authority> authorities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Authority authority = new Authority();
            authority.setName("Authority " + i);
            authority.setDescription("Authority Description");
            authority.setPermission("root:authority" + i);
            authorityService.save(authority);
            authorities.add(authority);
            ids.add(authority.getId());
        }
        List<Authority> result = authorityService.findByIds(ids);
        assertEquals(ids.size(), authorities.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(authorities.get(i).getId(), result.get(i).getId());
            assertEquals(authorities.get(i).getName(), result.get(i).getName());
        }
    }

    @Test
    void findByPermissions() {
        List<String> permissions = new ArrayList<>();
        List<Authority> authorities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Authority authority = new Authority();
            authority.setName("Authority " + i);
            authority.setDescription("Authority Description");
            authority.setPermission("root:permission-" + i);
            authorityService.save(authority);
            permissions.add(authority.getPermission());
            authorities.add(authority);
        }
        List<Authority> result = authorityService.findByPermissions(permissions);
        assertEquals(permissions.size(), authorities.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(authorities.get(i).getId(), result.get(i).getId());
            assertEquals(authorities.get(i).getPermission(), result.get(i).getPermission());
        }
    }
}