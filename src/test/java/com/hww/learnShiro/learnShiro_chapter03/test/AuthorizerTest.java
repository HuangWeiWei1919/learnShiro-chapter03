package com.hww.learnShiro.learnShiro_chapter03.test;

import org.junit.Assert;
import org.junit.Test;


public class AuthorizerTest extends BaseTest {

    @Test
    public void testIsPermitted() {
        login("classpath:shiro-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create 等等,在自定义的MyRealm中使用了user1:*这种通配方式，即这种资源所有的权限
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user1:delete"));
        Assert.assertTrue(subject().isPermitted("user1:view"));
        Assert.assertTrue(subject().isPermitted("user1:add"));
        
        Assert.assertTrue(subject().isPermitted("user2:update"));
        
        //通过二进制位的方式表示权限,+user1+10,+user2+10
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限 0110 & 0010 = 0010 true
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限 0110 & 0100 = 0100 true
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看 

        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
        
    }

    @Test
    public void testIsPermitted2() {
        login("classpath:shiro-jdbc-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(subject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }

}
