package com.codimiracle.web.middleware.user.service;

import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.pojo.vo.SocialUserVO;
import com.codimiracle.web.middleware.user.pojo.vo.UserMetadataVO;
import com.codimiracle.web.mybatis.contract.support.vo.Service;

public interface UserService extends Service<String, UserMetadata, UserMetadataVO> {
    SocialUserVO findSocialUserById(String userId);
    UserMetadata findByUsername(String username);
}
