package com.codimiracle.web.middleware.user.pojo.vo;

import com.codimiracle.web.middleware.user.inflation.UserProfileInflatable;
import com.codimiracle.web.middleware.user.pojo.eo.UserProfile;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
public class UserMetadataVO implements UserProfileInflatable {
    private String id;
    private String username;
    private String password;
    private String nickname;
    private String type;
    private String avatar;
    private Date expiredAt;
    private Date credentialsExpiredAt;
    private Boolean locked;
    private Boolean enabled;
    private Date createdAt;
    private Date updatedAt;

    private List<RoleVO> roleList;

    private String profileId;
    private UserProfile profile;

    public SocialUserVO toSocialUser() {
        SocialUserVO userSocial = new SocialUserVO();
        BeanUtils.copyProperties(this, userSocial);
        return userSocial;
    }
}
