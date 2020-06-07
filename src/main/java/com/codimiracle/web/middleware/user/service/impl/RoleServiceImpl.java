package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.vo.RoleVO;
import com.codimiracle.web.middleware.user.service.AuthorityService;
import com.codimiracle.web.middleware.user.service.RoleService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends AbstractService<String, Role, RoleVO> implements RoleService {
    private AuthorityService authorityService;
}
