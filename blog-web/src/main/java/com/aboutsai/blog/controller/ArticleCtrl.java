package com.aboutsai.blog.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aboutsai.blog.common.controller.BaseCtrl;
import com.aboutsai.blog.common.datadict.DataDictionary;
import com.aboutsai.blog.dto.ArticleInfoDto;
import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.blog.service.CatalogService;
import com.aboutsai.framework.util.DateUtil;
import com.aboutsai.framework.util.FileUtil;

@Controller
@RequestMapping({ "/article" })
public class ArticleCtrl extends BaseCtrl {
	// 图片上传支持类型W
	private String IMG_TYPE = "gif,jpg,jpeg,png,bmp";
	// 图片上传大小单位M
	private String IMG_SIZE = "1";
	@Resource
	private ArticleService articleService;
	@Resource
	private CatalogService catalogService;

	@RequestMapping({ "/index" })
	public String index(Model model) {
		ArticleInfoDto articleInfoDto = new ArticleInfoDto();
		model.addAttribute("articleList", articleService.query(articleInfoDto));

		Catalog catalog = new Catalog();
		model.addAttribute("catalogList", catalogService.query(catalog));

		super.setCurrentModule(model, DataDictionary.Module.article.name());
		return "article/articleIndex";
	}

	@RequestMapping({ "/toAdd" })
	public String toAdd(Model model, ArticleInfoDto articleInfoDto) {
		Catalog catalog = new Catalog();
		model.addAttribute("catalogList", this.catalogService.query(catalog));

		return "article/add";
	}

	@RequestMapping({ "/add" })
	public String add(Model model, ArticleInfoDto articleInfoDto) {
		this.articleService.save(articleInfoDto);
		return "redirect:/portal/index";
	}

	@RequestMapping({ "/publish" })
	public String publish(Model model, ArticleInfoDto articleInfoDto) {
		this.articleService.publish(articleInfoDto);
		return "redirect:/portal/index";
	}

	@RequestMapping({ "/view/{id}" })
	public String view(Model model, @PathVariable("id") String id) {
		ArticleInfo articleInfo = this.articleService.getArticleInfoById(id);
		ArticleContent articleContent = this.articleService.getArticleContentById(id);
		this.articleService.updateHits(articleInfo);

		ArticleInfoDto articleInfoDto = new ArticleInfoDto();
		BeanUtils.copyProperties(articleInfo, articleInfoDto);
		if (articleContent != null) {
			articleInfoDto.setArticleContent(articleContent.getArticleContent());
		}
		model.addAttribute("articleInfoDto", articleInfoDto);
		return "article/view";
	}

//	@RequestMapping({ "/fileUpload" })
//	@ResponseBody
//	public Map<String, Object> fileUpload(@RequestParam("imgFile") MultipartFile imgFile, HttpServletRequest request,
//			HttpServletResponse response) {
//		// 定义允许上传的文件扩展名
//		try {
//			String extString = IMG_TYPE;
//
//			String fileName = imgFile.getOriginalFilename();
//			InputStream inputStream = imgFile.getInputStream();
//			// 检查扩展名
//			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
//			if (!Arrays.<String>asList(extString.split(",")).contains(fileExt)) {
//				return getError("仅支持以下格式文件：" + extString);
//			}
//
//			if (inputStream.available() / 1000 / 1000 > Integer.parseInt(IMG_SIZE)) {
//				return getError("上传文件不能大于" + Integer.parseInt(IMG_SIZE) + "M");
//			}
//			String uploadPath = FileUtil.UPLOAD_PATH + DateUtil.getCurrentYearMonth() + "/";
//
//			String path = request.getServletContext().getRealPath("/") + uploadPath;
//			File dir = new File(path);
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//			String picture = new Date().getTime() + "." + fileExt;
//			path = path + picture;
//			File file = new File(path);
//			FileUtil.copyInputStreamToFile(imgFile.getInputStream(), file);
//
//			Map<String, Object> succMap = new HashMap<String, Object>();
//			succMap.put("error", 0);
//			String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
//					+ uploadPath + "/" + picture;
//			succMap.put("url", url);
//			return succMap;
//		} catch (IOException e1) {
//			e1.printStackTrace();
//			return getError("上传文件失败。");
//		}
//	}

	private Map<String, Object> getError(String errorMsg) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
		errorMap.put("error", 1);
		errorMap.put("message", errorMsg);
		return errorMap;
	}

	public String getModuleName() {
		return this.getClass().getSimpleName();
	}
}
