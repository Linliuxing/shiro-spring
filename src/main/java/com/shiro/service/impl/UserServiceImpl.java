package com.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shiro.dao.UserMapper;
import com.shiro.domain.User;
import com.shiro.service.UserService;

/**
 * @author 林尤庆
 * @date 2018年3月23日 上午11:16:07
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;

	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	public List<String> findPermissionByUserId(Integer userId) {
		
		return userMapper.findPermissionByUserId(userId);
	}
	
}
