package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.mapper.AuthorityMapper;
import com.codimiracle.web.middleware.user.pojo.po.Authority;
import com.codimiracle.web.middleware.user.pojo.vo.AuthorityVO;
import com.codimiracle.web.middleware.user.service.AuthorityService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

@Service
@Transactional
public class AuthorityServiceImpl extends AbstractService<String, Authority, AuthorityVO> implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<Authority> findByIds(List<String> ids) {
        Condition condition = new Condition(Authority.class);
        condition.createCriteria()
                .andIn("id", ids);
        return findByCondition(condition);
    }

    @Override
    public List<Authority> findByPermissions(List<String> permissions) {
        Condition condition = new Condition(Authority.class);
        condition.createCriteria()
                .andIn("permission", permissions);
        return findByCondition(condition);
    }
}
