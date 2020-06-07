package com.codimiracle.web.middleware.user.service;

import com.codimiracle.web.middleware.user.pojo.po.Authority;
import com.codimiracle.web.middleware.user.pojo.vo.AuthorityVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

import java.util.List;

public interface AuthorityService extends Service<String, Authority, AuthorityVO> {
    List<Authority> findByIds(List<String> ids);

    List<Authority> findByPermissions(List<String> permissions);
}
