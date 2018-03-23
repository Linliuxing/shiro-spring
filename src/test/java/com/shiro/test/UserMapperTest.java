package com.shiro.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shiro.dao.UserMapper;
import com.shiro.domain.User;

/**
 * @author 林尤庆
 * @date 2018年3月23日 上午10:46:09
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserMapperTest {
	
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void testFindByName(){
		User user = userMapper.findByName("jack");
		System.out.println(user);
	}

}
