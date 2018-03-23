package com.shiro.service;

import java.util.List;

import com.shiro.domain.User;

/**
 * @author 林尤庆
 * @date 2018年3月23日 上午11:14:42
 * @version 1.0
 */
public interface UserService {
	public User findByName(String name);
	
	public List<String> findPermissionByUserId(Integer userId);
}
