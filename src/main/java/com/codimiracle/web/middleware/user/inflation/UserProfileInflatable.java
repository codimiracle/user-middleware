package com.codimiracle.web.middleware.user.inflation;

import com.codimiracle.web.middleware.user.pojo.eo.UserProfile;
import com.codimiracle.web.mybatis.contract.support.vo.inflation.Inflatable;

/**
 * inflate {@link UserProfile}
 * @author Codimiracle
 */
public interface UserProfileInflatable extends Inflatable {
    void setProfile(UserProfile profile);
    UserProfile getProfile();
    String getProfileId();
}
