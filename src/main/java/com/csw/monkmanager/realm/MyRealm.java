package com.csw.monkmanager.realm;

import com.csw.monkmanager.dao.AdminDao;
import com.csw.monkmanager.entity.Admin;
import com.csw.monkmanager.entity.Resource;
import com.csw.monkmanager.entity.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AdminDao adminDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        Admin admin = adminDao.queryAdminInfo(primaryPrincipal);
       //admin]]]]]" + admin);
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> resources = new ArrayList<>();
        List<Role> roles1 = admin.getRoles();
        for (Role rr : roles1) {
            roles.add(rr.getRole_name());
            for (Resource ee : rr.getResources()) {
                resources.add(ee.getResource_name());
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(resources);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        Admin admin = new Admin();
        admin.setUsername(principal);
        Admin admin1 = adminDao.selectOne(admin);
        return new SimpleAuthenticationInfo(admin1.getUsername(), admin1.getPassword(), this.getName());
    }
}
