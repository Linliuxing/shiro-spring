package com.shiro.dao;

import java.util.List;

import com.shiro.domain.User;

/**
 * @author 林尤庆
 * @date 2018年3月23日 上午10:40:08
 * @version 1.0
 */
public interface UserMapper {
	public User findByName(String name);
	public List<String> findPermissionByUserId(Integer userId);
}
