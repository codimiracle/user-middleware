package com.codimiracle.web.middleware.user.mapper;

import com.codimiracle.web.middleware.user.pojo.po.UserRole;
import com.codimiracle.web.middleware.user.pojo.vo.UserRoleVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserRolesMapper extends Mapper<UserRole, UserRoleVO> {
    List<UserRoleVO> selectByUserIdIntegrally(String userId);
}
