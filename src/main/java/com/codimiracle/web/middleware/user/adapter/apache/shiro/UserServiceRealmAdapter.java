package com.codimiracle.web.middleware.user.adapter.apache.shiro;

import com.codimiracle.web.middleware.user.pojo.po.Role;
import com.codimiracle.web.middleware.user.pojo.po.UserMetadata;
import com.codimiracle.web.middleware.user.service.UserService;
import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class UserServiceRealmAdapter extends AuthorizingRealm {
    @Getter
    @Setter
    private boolean permissionsLookupEnabled = false;

    private final UserService userService;

    public UserServiceRealmAdapter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);

        UserMetadata userMetadata = userService.findByUsername(username);

        List<Role> roleList = userMetadata.getRoleList();

        // Retrieve roles and permissions from database
        Set<String> roleNames = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        for (Role role : roleList) {
            roleNames.add(role.getIdentifier());
            if (permissionsLookupEnabled) {
                permissions.addAll(Splitter.on(",")
                        .omitEmptyStrings()
                        .splitToList(role.getAuthorities()));
            }
        }


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        SimpleAuthenticationInfo info;
        String password;
        String salt;

        UserMetadata userMetadata = userService.findByUsername(username);
        password = userMetadata.getPassword();
        salt = userMetadata.getPasswordSalt();

        if (password == null) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }

        info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());

        if (salt != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(salt));
        }

        return info;
    }
}
