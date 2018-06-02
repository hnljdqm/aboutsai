package com.aboutsai.blog.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aboutsai.blog.api.dto.ExecResult;
import com.aboutsai.blog.api.dto.SysUserDto;
import com.aboutsai.blog.common.page.PageInfo;
import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.entity.User;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.blog.service.CatalogService;
import com.aboutsai.blog.service.UserService;
import com.github.pagehelper.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="ApiController测试类上的注解")
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
	@ApiOperation(value="文章详情接口", notes="根据url的id来查看文章详情接口")
	@ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "String", paramType = "path")
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
	public ExecResult login(@RequestBody SysUserDto userDto, HttpServletResponse response) {
		// 指定允许其他域名访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		User user = userService.login(userDto.getUserName());
		if (user == null) {
			return new ExecResult(false, "101", "用户名或密码不存在");
		}
		//TODO 密码加密处理
		if (!user.getPassword().equals(userDto.getPassword())) {
			return new ExecResult(false, "101", "用户名或密码不存在");
		}
		return new ExecResult(true, "200", "登录成功");
	}
	
	/**
	 * 发表文章接口
	 * @author hnljd
	 * @date 2018年4月3日 下午10:28:08
	 * @param model
	 * @param articleInfo
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.POST)
	@ResponseBody
	public ExecResult articles(Model model,@RequestBody ArticleInfo articleInfo) {
		if (StringUtil.isEmpty(articleInfo.getTitle())) {
			return new ExecResult(true, "201", "标题不能为空");
		}
		if (StringUtil.isEmpty(articleInfo.getArticleContent())) {
			return new ExecResult(true, "202", "内容不能为空");
		}
		if (StringUtil.isEmpty(articleInfo.getCatalogId())) {
			return new ExecResult(true, "202", "文章分类不能为空");
		}
		this.articleService.publish(articleInfo);
		return new ExecResult(true, "200", "发表文章成功");
	}
}
