package com.gxx.tools.web.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gxx.tools.base.vo.User;
import com.gxx.tools.dto.UserDto;
import com.gxx.tools.service.UserService;

/**
 * QueryAllocateController负责查询调拨
 * 
 * @author gxx
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	/**
	 * 日志处理器
	 */
	private final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/preRegistFtl", method = RequestMethod.GET)
	public String preRegistFtl() {
		return "user/preRegistFtl";
	}
	
	@RequestMapping(value = "/preRegistJsp", method = RequestMethod.GET)
	public String preRegistJsp() {
		return "user/preRegistJsp";
	}
	
	/**
	 * 注册
	 * @param request
	 * @param userDto
	 * @return
	 */
	@RequestMapping(value = "/registJsp",produces="application/json")
	public @ResponseBody UserDto registJsp(HttpServletRequest request, UserDto userDto) {
		logger.info("用户注册：姓名[" + userDto.getName() + "]，密码[" + userDto.getPassword() + "]");
		/**
		 * 1.判用户名是否存在
		 */
		User user = userService.getUserByName(userDto.getName());
		if(user != null){
			userDto.setSuccess(Boolean.FALSE.booleanValue());
			userDto.setMessage("用户名[" + userDto.getName() + "]已存在!");
			return userDto;
		}
		/**
		 * 2.创建用户对象 并 新增用户
		 */
		user = new User();
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setCreateDate("20150618");
		user.setCreateTime("000000");
		userService.doSaveUser(user);
		/**
		 * 3.返回结果
		 */
		userDto.setSuccess(Boolean.TRUE.booleanValue());
		userDto.setMessage("注册成功!");
		return userDto;
	}
	
	/**
	 * 注册
	 * @param request
	 * @param userDto
	 * @return
	 */
	@RequestMapping(value = "/registFtl")
	public String registFtl(HttpServletRequest request, UserDto userDto) {
		logger.info("用户注册：姓名[" + userDto.getName() + "]，密码[" + userDto.getPassword() + "]");
		/**
		 * 1.判用户名是否存在
		 */
		User user = userService.getUserByName(userDto.getName());
		if(user != null){
			userDto.setSuccess(Boolean.FALSE.booleanValue());
			userDto.setMessage("用户名[" + userDto.getName() + "]已存在!");
			return "user/result";
		}
		/**
		 * 2.创建用户对象 并 新增用户
		 */
		user = new User();
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setCreateDate("20150618");
		user.setCreateTime("000000");
		userService.doSaveUser(user);
		/**
		 * 3.返回结果
		 */
		userDto.setSuccess(Boolean.TRUE.booleanValue());
		userDto.setMessage("注册成功!");
		return "user/result";
	}
}