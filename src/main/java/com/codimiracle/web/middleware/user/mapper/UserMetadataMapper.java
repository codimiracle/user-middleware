package com.codimiracle.web.middleware.user.mapper;

import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.pojo.vo.SocialUserVO;
import com.codimiracle.web.middleware.user.pojo.vo.UserMetadataVO;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMetadataMapper extends Mapper<UserMetadata, UserMetadataVO> {
    SocialUserVO selectByUserIdProtectlly(String userId);
}
