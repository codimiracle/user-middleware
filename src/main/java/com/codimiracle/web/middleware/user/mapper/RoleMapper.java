package com.codimiracle.web.middleware.user.mapper;

import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.vo.RoleVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface RoleMapper extends Mapper<Role, RoleVO> {
}
