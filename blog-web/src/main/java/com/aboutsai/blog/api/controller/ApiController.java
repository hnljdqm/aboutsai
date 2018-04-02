package com.aboutsai.blog.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aboutsai.blog.api.dto.ExecResult;
import com.aboutsai.blog.common.page.PageInfo;
import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.entity.User;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.blog.service.CatalogService;
import com.aboutsai.blog.service.UserService;

@Controller
@RequestMapping("/api/blog")
public class ApiController {
	@Resource
	private ArticleService articleService;
	@Resource
	private CatalogService catalogService;
	@Resource
	private UserService userService;

	/**
	 * 文章分类接口
	 * @author hnljd
	 * @date 2018年3月26日 下午9:55:06
	 * @return
	 */
	@RequestMapping(value = "/catalogs", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<Catalog>> query(HttpServletResponse response) {
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		Catalog catalog = new Catalog();
		List<Catalog> catalogList = catalogService.query(catalog);

		Map<String, List<Catalog>> map = new HashMap<String, List<Catalog>>();
		map.put("list", catalogList);
		return map;
	}

	/**
	 * 文章列表接口
	 * @author hnljd
	 * @date 2018年3月26日 下午9:54:45
	 * @param pageNum
	 * @param pageSize
	 * @param title
	 * @param catalogId
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<ArticleInfo> articles(@RequestParam("pageNum") Integer pageNum, 
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "catalogId", required = false) String catalogId,
			HttpServletResponse response) {
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		ArticleInfo articleInfo = new ArticleInfo();
		articleInfo.setCatalogId(catalogId);
		return articleService.query(articleInfo, pageNum, pageSize);
	}
	
	/**
	 * 文章详情接口
	 * @author hnljd
	 * @date 2018年3月26日 下午9:54:58
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ArticleInfo articles(@PathVariable("id") String id,HttpServletResponse response) {
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		ArticleInfo articleInfo = articleService.getArticleInfoById(id);
		ArticleContent articleContent = articleService.getArticleContentById(id);
		articleInfo.setArticleContent(articleContent.getArticleContent());
		articleService.updateHits(articleInfo);
		return articleInfo;
	}
	
	/**
	 * 登录
	 * @author hnljd
	 * @date 2018年4月1日 下午3:46:46
	 * @param userName
	 * @param password
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ExecResult login(@RequestParam("userName") String userName, 
			@RequestParam("password") String password, HttpServletResponse response) {
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		User user = userService.login(userName);
		if (user == null) {
			return new ExecResult(false, "101", "用户名或密码不存在");
		}
		//TODO 密码加密处理
		if (!user.getPassword().equals(password)) {
			return new ExecResult(false, "101", "用户名或密码不存在");
		}
		return new ExecResult(true, "200", "登录成功");
	}
}
