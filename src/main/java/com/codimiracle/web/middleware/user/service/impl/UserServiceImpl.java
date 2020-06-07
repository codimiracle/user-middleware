package com.codimiracle.web.middleware.user.service.impl;

import com.codimiracle.web.middleware.user.mapper.UserMetadataMapper;
import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.pojo.vo.SocialUserVO;
import com.codimiracle.web.middleware.user.pojo.vo.UserMetadataVO;
import com.codimiracle.web.middleware.user.service.UserRolesService;
import com.codimiracle.web.middleware.user.service.UserService;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<String, UserMetadata, UserMetadataVO> implements UserService {
    @Resource
    private UserMetadataMapper metadataMapper;
    @Resource
    private UserRolesService userRolesService;

    @Override
    protected UserMetadataVO mutate(UserMetadataVO inflatedObject) {
        inflatedObject.setRoleList(userRolesService.findRoleByUserIdIntegrally(inflatedObject.getId()));
        return super.mutate(inflatedObject);
    }

    @Override
    public void save(UserMetadata model) {
        model.setCreatedAt(new Date());
        model.setUpdatedAt(model.getCreatedAt());
        super.save(model);
        if (Objects.nonNull(model.getRoleList())) {
            userRolesService.updateAttachingRoles(model.getId(), model.getRoleList());
        }
    }

    @Override
    public SocialUserVO findSocialUserById(String userId) {
        return metadataMapper.selectByUserIdProtectlly(userId);
    }

    @Override
    public UserMetadata findByUsername(String username) {
        return findBy("username", username);
    }
}
