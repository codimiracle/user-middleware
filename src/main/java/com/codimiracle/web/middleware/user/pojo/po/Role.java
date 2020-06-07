package com.codimiracle.web.middleware.user.pojo.po;

import com.codimiracle.web.mybatis.contract.annotation.LogicDelete;
import com.codimiracle.web.mybatis.contract.annotation.LogicDeletedDate;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String identifier;
    private String description;
    private String authorities;
    @Transient
    private List<Authority> authorityList;
    @LogicDelete
    private Boolean deleted;
    @LogicDeletedDate
    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}
