package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.vo.RoleVO;
import com.codimiracle.web.middleware.user.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceImplTest {
    @Resource
    private RoleService roleService;

    @Test
    void findByIdIntegrally() {
        Role role = new Role();
        role.setName("角色 1");
        role.setDescription("这个是测试角色！");
        role.setAuthorities("user:all,role:all");
        roleService.save(role);
        RoleVO result = roleService.findByIdIntegrally(role.getId());
        assertEquals(role.getName(), result.getName());
        assertEquals(role.getDescription(), result.getDescription());
    }
}