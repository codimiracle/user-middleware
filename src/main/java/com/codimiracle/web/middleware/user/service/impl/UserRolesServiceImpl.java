package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.mapper.UserRolesMapper;
import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.po.UserRole;
import com.codimiracle.web.middleware.user.pojo.vo.RoleVO;
import com.codimiracle.web.middleware.user.pojo.vo.UserRoleVO;
import com.codimiracle.web.middleware.user.service.RoleService;
import com.codimiracle.web.middleware.user.service.UserRolesService;
import com.codimiracle.web.middleware.user.service.UserService;
import com.codimiracle.web.mybatis.contract.ServiceException;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRolesServiceImpl extends AbstractService<String, UserRole, UserRoleVO> implements UserRolesService {
    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;

    @Resource
    private UserRolesMapper userRolesMapper;

    private List<UserRole> findByUserId(String userId, boolean withDeleted) {
        Condition condition = new Condition(UserRole.class);
        condition.createCriteria()
                .andEqualTo("userId", userId);
        if (!withDeleted) {
            condition.and().andEqualTo("deleted", false);
        }
        return findByCondition(condition);
    }

    @Override
    public List<UserRole> findByUserId(String userId) {
        return findByUserId(userId, false);
    }

    @Override
    public List<RoleVO> findRoleByUserIdIntegrally(String userId) {
        List<UserRoleVO> userRoleList = findByUserIdIntegrally(userId);
        return userRoleList.stream().map(UserRoleVO::getRole).collect(Collectors.toList());
    }

    @Override
    protected UserRoleVO mutate(UserRoleVO inflatedObject) {
        if (Objects.nonNull(inflatedObject)) {
            inflatedObject.setRole(roleService.findByIdIntegrally(inflatedObject.getId()));
            inflatedObject.setUser(userService.findByIdIntegrally(inflatedObject.getId()));
        }
        return super.mutate(inflatedObject);
    }

    @Override
    public List<UserRoleVO> findByUserIdIntegrally(String userId) {
        List<UserRoleVO> userRoleList = userRolesMapper.selectByUserIdIntegrally(userId);
        userRoleList.forEach(this::mutate);
        return userRoleList;
    }

    @Override
    public void updateAttachingRoles(String userId, List<Role> roleList) {
        Objects.requireNonNull(userId, "user id can not be null!");
        Objects.requireNonNull(roleList, "role list can not be null!");
        // mapping to Map<roleId, UserRole>
        Map<String, UserRole> needToAttachRoles = roleList.stream().collect(Collectors.toMap(Role::getId, (e) -> {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(e.getId());
                    return userRole;
                }
        ));
        if (needToAttachRoles.containsKey(null)) {
            throw new ServiceException("Each role must have their role id");
        }
        // exists attached roles
        List<UserRole> attachedUserRoles = findByUserIdWithDeleted(userId);
        // process attached roles
        attachedUserRoles.forEach((r) -> {
            if (needToAttachRoles.containsKey(r.getRoleId())) {
                r.setDeleted(false);
            } else {
                r.setDeleted(true);
            }
            // remove exists roles, update deleted state later.
            needToAttachRoles.remove(r.getRoleId());
        });
        // needToAttachRoles is need to append now
        if (!needToAttachRoles.isEmpty()) {
            save(new ArrayList<>(needToAttachRoles.values()));
        }
        // update exists roles state.
        attachedUserRoles.forEach(this::update);
    }

    private List<UserRole> findByUserIdWithDeleted(String userId) {
        return findByUserId(userId, true);
    }
}
