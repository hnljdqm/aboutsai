package com.aboutsai.blog.service;

import com.aboutsai.blog.entity.User;
import com.aboutsai.framework.page.PageInfo;

public interface UserService {
	/**
	 * 根据用户ID获取用户信息
	 * @param id 用户ID
	 * @return
	 */
	public User findById(String id);

	/**
	 * 新增2个用户 for 测试事务
	 * @return
	 */
	public void save();
	
	/**
	 * 分页查询用户列表
	 * @param user
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<User> query(User user, int pageNo, int pageSize);
	
}
