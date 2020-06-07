package com.codimiracle.web.middleware.user.pojo.vo;

import com.google.common.base.Splitter;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
public class RoleVO {
    private String id;
    private String name;
    private String description;
    private String authorities;

    private Date createdAt;
    private Date updatedAt;

    @Transient
    private List<String> getAuthorityList() {
        return Splitter.on(",").splitToList(this.authorities);
    }
}
