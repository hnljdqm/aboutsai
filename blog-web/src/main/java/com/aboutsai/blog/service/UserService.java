package com.aboutsai.blog.service;

import com.aboutsai.blog.common.page.PageInfo;
import com.aboutsai.blog.entity.User;

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
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<User> query(User user, int pageNum, int pageSize);
	
	/**
     * 登录
     * @author hnljd
     * @date 2018年4月1日 下午3:41:17
     * @param userName
     * @return
     */
    public User login(String userName);
	
}
