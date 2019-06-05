package com.baizhi.shiro;

import com.baizhi.entity.Admin;
import com.baizhi.entity.RoleDto;
import com.baizhi.service.AdminService;
import com.baizhi.service.UserService;
import com.baizhi.utils.SpringContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyRealmWeb extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AdminService adminService = (AdminService)SpringContextUtil.getBean(AdminService.class);
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        Admin admin = adminService.findByName(primaryPrincipal);
        List<RoleDto> roles = adminService.findByTokenName(primaryPrincipal);
        HashSet<String> set = new HashSet<>();
        ArrayList<String> arrayList = new ArrayList<>();
        //主体  查角色  插权限
        SimpleAuthorizationInfo authorizationInfo = null;
        if(primaryPrincipal.equals(admin.getUsername())){
            authorizationInfo = new SimpleAuthorizationInfo();

            for(RoleDto role:roles){
                arrayList.add(role.getRoleName());
                String id = role.getId();
                List<String> permissionss = adminService.findByPermission(id);
                for(String key:permissionss){
                    set.add(key);
                }
            }

            authorizationInfo.addStringPermissions(set);
            authorizationInfo.addRoles(arrayList);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        AdminService adminService = (AdminService)SpringContextUtil.getBean(AdminService.class);
        String principal = (String) authenticationToken.getPrincipal();
        Admin admin = adminService.findByName(principal);
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if(principal.equals(admin.getUsername())){
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),this.getName());
        }
        return simpleAuthenticationInfo;
    }
}
