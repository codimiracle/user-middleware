package com.codimiracle.web.middleware.user.service;

import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.po.UserRole;
import com.codimiracle.web.middleware.user.pojo.vo.RoleVO;
import com.codimiracle.web.middleware.user.pojo.vo.UserRoleVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;

public interface UserRolesService extends Service<String, UserRole, UserRoleVO> {
    List<UserRole> findByUserId(String userId);

    List<RoleVO> findRoleByUserIdIntegrally(String userId);

    List<UserRoleVO> findByUserIdIntegrally(String userId);

    void updateAttachingRoles(String userId, List<Role> roleList);
}
