package com.aboutsai.blog.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.blog.service.CatalogService;

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
}
