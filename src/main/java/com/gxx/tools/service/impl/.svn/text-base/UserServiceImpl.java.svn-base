package com.gxx.tools.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxx.tools.base.dao.UserMapper;
import com.gxx.tools.base.vo.User;
import com.gxx.tools.service.UserService;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	用户服务实现类
 *    </dd>
 *    <dt><b>Description:</b></dt>
 *    <dd>
 *    	<p>none
 *    </dd>
 * </dl>
 *
 * @author Administrator
 * @version 1.0, 2015年6月18日
 * @since tools
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;

	/**
	 * 新增用户
	 * @param user
	 */
	public void doSaveUser(User user) {
		userDao.insert(user);
	}

	/**
	 * 根据姓名查用户
	 * @param name
	 * @return
	 */
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}
}
