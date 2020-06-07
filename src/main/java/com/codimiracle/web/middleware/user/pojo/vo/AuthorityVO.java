package com.codimiracle.web.middleware.user.pojo.vo;

import lombok.Data;

@Data
public class AuthorityVO {
    private String id;
    private String name;
    private String description;
    private String permission;
}
