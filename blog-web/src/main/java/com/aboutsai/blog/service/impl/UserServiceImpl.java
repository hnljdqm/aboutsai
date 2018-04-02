package com.aboutsai.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aboutsai.blog.common.page.PageInfo;
import com.aboutsai.blog.dao.UserDao;
import com.aboutsai.blog.entity.User;
import com.aboutsai.blog.service.UserService;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }
    
//    @Transactional
	@Override
	public void save() {
		User user1 = new User("3", "A", "A@qq.com");
		userDao.insert(user1);
		
		User user2 = new User("4", "BBB", "BBB@qq.com");
		userDao.insert(user2);
	}

	@Override
	public PageInfo<User> query(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<User>(userDao.query(user));
	}
	
	/**
     * 登录
     * @author hnljd
     * @date 2018年4月1日 下午3:41:17
     * @param userName
     * @return
     */
    public User login(String userName) {
    	return userDao.login(userName);
    }
	
}
