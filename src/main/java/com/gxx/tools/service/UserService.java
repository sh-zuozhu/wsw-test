package com.gxx.tools.service;

import com.gxx.tools.base.vo.User;

/**
 * <dl>
 *    <dt><b>Title:</b></dt>
 *    <dd>
 *    	用户服务接口
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
public interface UserService {
	/**
	 * 新增用户
	 * @param user
	 */
	public void doSaveUser(User user);
	
	/**
	 * 根据姓名查用户
	 * @param name
	 * @return
	 */
	public User getUserByName(String name);
}
