package com.hww.learnShiro_chapter03.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * 
 * @Description: 根据角色字符串来解析得到权限集合
 * @author: Huang Gai
 * @time: 2015年9月24日 下午1:48:09
 *
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if("role1".equals(roleString)) {
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));
        }
        return null;
    }
}
