package com.codimiracle.web.middleware.user.pojo.po;

import com.codimiracle.web.mybatis.contract.annotation.LogicDelete;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;

    @LogicDelete
    private Boolean deleted;
}
