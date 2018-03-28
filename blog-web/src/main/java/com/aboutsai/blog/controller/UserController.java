package com.aboutsai.blog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aboutsai.blog.common.page.PageInfo;
import com.aboutsai.blog.entity.User;
import com.aboutsai.blog.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;

	/**
	 * 查询分页查询
	 * @param user
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<User> query(User user, 
			@RequestParam("pageNum") int pageNum, 
			@RequestParam("pageSize") int pageSize) {
		return userService.query(user, pageNum, pageSize);
	}
}
