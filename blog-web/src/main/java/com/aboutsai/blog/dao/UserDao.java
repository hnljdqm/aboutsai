package com.aboutsai.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aboutsai.blog.entity.User;

public interface UserDao {

	/**
	 * 根据用户ID获取用户信息
	 * @param id 用户ID
	 * @return
	 */
    public User findById(@Param(value = "id") String id);
    
    /**
     * 用户查询
     * @param user
     * @return
     */
    public List<User> query(User user);

    /**
	 * 新增用户
	 * @param user
	 * @return
	 */
    public void insert(User user);
}
