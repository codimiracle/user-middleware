package com.codimiracle.web.middleware.user.pojo.po;

import com.codimiracle.web.mybatis.contract.annotation.LogicDelete;
import com.codimiracle.web.mybatis.contract.annotation.LogicDeletedDate;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * represents a permission, it will describe the permission functionality.
 *
 * @author Codimiracle
 */
@Data
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String permission;
    private String description;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @LogicDelete
    private Boolean deleted;
    @LogicDeletedDate
    private Boolean deletedAt;
}
