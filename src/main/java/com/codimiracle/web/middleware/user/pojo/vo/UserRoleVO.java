package com.codimiracle.web.middleware.user.pojo.vo;

import lombok.Data;

@Data
public class UserRoleVO {
    private String id;
    private String userId;
    private UserMetadataVO user;
    private String roleId;
    private RoleVO role;
}
