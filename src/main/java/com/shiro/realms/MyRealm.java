package com.shiro.realms;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.alibaba.druid.util.StringUtils;
import com.shiro.domain.User;
import com.shiro.service.UserService;

/**
 * @author 林尤庆
 * @date 2018年3月22日 下午4:42:34
 * @version 1.0
 */
public class MyRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 基于资源的授权
		// info.addStringPermission("product:add");

		// 给当前登录用户进行动态授权
		// 1.获取当前用户的 pricipal
		Subject subject = SecurityUtils.getSubject();
		User dbUser = (User) subject.getPrincipal();
		// 2.查询当前用户拥有的资源授权码
		List<String> perms = userService.findPermissionByUserId(dbUser.getId());
		if (perms != null) {
			// 遍历授权
			for (String perm : perms) {
				if (!StringUtils.isEmpty(perm)) {
					info.addStringPermission(perm);
				}
			}
		}

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行授权方法...");

		// 1.获取用户输入的账户信息
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;

		/*
		 * // 模拟数据库的密码 String name = "jack"; String password = "1234";
		 * 
		 * if (!token.getUsername().equals(name)) { // 用户不存在 return null; }
		 */

		// 实现动态认证
		User dbUser = userService.findByName(token.getUsername());
		if (dbUser == null) {
			// 用户不存在
			return null;
		}

		// 返回密码
		return new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), "");
	}

}
