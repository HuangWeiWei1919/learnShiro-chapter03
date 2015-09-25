package com.hww.learnShiro.learnShiro_chapter03.test;

import java.util.Arrays;




import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class RoleTest extends BaseTest{
	
	/**
	 * 
	 * @Description: 测试是否有权限
	 * @time: 2015年9月23日 下午4:37:35
	 */
	@Test
	public void testHasRole() {
		login("classpath:shiro-role.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		
		//判断拥有角色role1
		Assert.assertTrue(subject.hasRole("role1"));
		
		//判断拥有角色role1，role2
		Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
		
		//判断拥有角色role1，role2，role3
		boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);	
	}
	
	/**
	 * 
	 * @Description: 测试是否有某角色
	 * @time: 2015年9月23日 下午4:37:21
	 */
	@Test(expected=UnauthorizedException.class)
	public void testCheckRole() {
		login("classpath:shiro-role.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		
		//断言有角色role1
		subject.checkRole("role1");
		
		//断言有角色role1,role3抛出异常
		subject.checkRoles(Arrays.asList("role1", "role3"));
	}
	
	/**
	 * 
	 * @Description: 测试是否有某权限
	 * @time: 2015年9月23日 下午4:36:23
	 */
	@Test
	public void testIsPermitted() {
		login("classpath:shiro-permission.ini", "zhang", "123");

		Subject subject = SecurityUtils.getSubject();
		//断言有权限：user:create
		Assert.assertTrue(subject.isPermitted("user:create"));
		
		//断言有权限：user:create 和 user:update
		Assert.assertTrue(subject.isPermittedAll("user:create", "user:update"));
		
		//断言没有权限user:view
		Assert.assertFalse(subject.isPermitted("user:view"));
		
	}
	/**
	 * 
	 * @Description: 检查有没有权限
	 * @time: 2015年9月23日 下午4:41:36
	 */
	@Test(expected=UnauthorizedException.class)
	public void checkPermitted() {
		login("classpath:shiro-permission.ini", "zhang", "123");

		Subject subject = SecurityUtils.getSubject();
		//断言有权限：user:create
		 subject.checkPermission("user:create");
		
		//断言有权限：user:delete 和 user:update
		 subject.checkPermissions("user:delete", "user:update");
		
		//断言有权限user:view，抛出异常
		subject.checkPermission("user:view");
	}
	
	
}
