package com.codimiracle.web.middleware.user.service;

import com.codimiracle.web.middleware.user.pojo.po.UserAccountPart;
import com.codimiracle.web.middleware.user.pojo.vo.UserAccountVO;
import com.codimiracle.web.mybatis.contract.Service;

import java.util.List;

public interface AccountService extends Service<String, UserAccountPart> {
    List<UserAccountPart> findByUserId(String userId);

    UserAccountVO findByUserIdForBalance(String userId);
}
