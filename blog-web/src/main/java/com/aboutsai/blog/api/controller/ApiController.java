package com.aboutsai.blog.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.blog.service.CatalogService;
import com.aboutsai.framework.page.PageInfo;

@Controller
@RequestMapping("/api/blog")
public class ApiController {
	@Resource
	private ArticleService articleService;
	@Resource
	private CatalogService catalogService;

	/**
	 * 文章分类接口
	 * @return
	 */
	@RequestMapping(value = "/catalogs", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<Catalog>> query() {
		Catalog catalog = new Catalog();
		List<Catalog> catalogList = catalogService.query(catalog);

		Map<String, List<Catalog>> map = new HashMap<String, List<Catalog>>();
		map.put("results", catalogList);
		return map;
	}

	/**
	 * 文章列表接口
	 * @return
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo<ArticleInfo> articles(@RequestParam("pageNum") Integer pageNum, 
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam(value = "title", required = false) String title) {
		ArticleInfo articleInfoDto = new ArticleInfo();
		return articleService.query(articleInfoDto, pageNum, pageSize);
	}
	
	/**
	 * 文章详情接口
	 * @return
	 */
	@RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ArticleInfo articles(@PathVariable("id") String id) {
		ArticleInfo articleInfo = articleService.getArticleInfoById(id);
		ArticleContent articleContent = articleService.getArticleContentById(id);
		articleInfo.setArticleContent(articleContent.getArticleContent());
		articleService.updateHits(articleInfo);
		return articleInfo;
	}
}
