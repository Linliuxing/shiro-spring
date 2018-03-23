package com.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.domain.User;

/**
 * @author 林尤庆
 * @date 2018年3月22日 下午5:00:14
 * @version 1.0
 */

@Controller
@RequestMapping("/user")
public class UserController {

		/**
		 * 登录
		 */
		@RequestMapping("/login")
		public String login(User user,HttpServletRequest request,Model model){
			
			//使用Shiro的认证操作
			//1. 获取Subject对象
			Subject subject = SecurityUtils.getSubject();
			
			//2.封装用户信息
			AuthenticationToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
			
			//3.执行认证
			try {
				subject.login(token);
				
				User dbUser = (User)subject.getPrincipal();
				
				request.getSession().setAttribute("userName", dbUser.getName());
				
				//登录成功
				return "redirect:/index";
			} catch (UnknownAccountException e) {
				model.addAttribute("msg", "用户不存在");
			} catch (IncorrectCredentialsException e) {
				model.addAttribute("msg", "密码输入有误");
			}
			return "login";
		}
	}

