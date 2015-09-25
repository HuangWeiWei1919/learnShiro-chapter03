package com.hww.learnShiro_chapter03.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;



public class BitAndWildPermissionResolver implements PermissionResolver {

    @Override
    public Permission resolvePermission(String permissionString) {
    	//以+ 开头，使用自定义的按位与的权限配置
        if(permissionString.startsWith("+")) {
            return new BitPermission(permissionString);
        }
        //否则，使用通配符方式
        return new WildcardPermission(permissionString);
    }
}
