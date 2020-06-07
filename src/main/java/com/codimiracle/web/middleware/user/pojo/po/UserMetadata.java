package com.codimiracle.web.middleware.user.pojo.po;

import com.codimiracle.web.mybatis.contract.annotation.LogicDelete;
import com.codimiracle.web.mybatis.contract.annotation.LogicDeletedDate;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "user_metadata")
public class UserMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String password;
    @Column(name = "password_salt")
    private String passwordSalt;
    private String nickname;
    private String type;
    private String avatar;
    @Column(name = "expired_at")
    private Date expiredAt;
    @Column(name = "credentials_expired_at")
    private Date credentialsExpiredAt;
    private Boolean locked;
    private Boolean enabled;
    @Column(name = "profile_id")
    private String profileId;

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @LogicDelete
    private Boolean deleted;
    @LogicDeletedDate
    private Boolean deletedAt;

    @Transient
    private List<Role> roleList;
}
