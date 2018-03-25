package com.aboutsai.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aboutsai.blog.entity.ArticleInfo;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.blog.service.CatalogService;

@Controller
@RequestMapping({ "/article" })
public class ArticleCtrl {
	// 图片上传支持类型W
	private String IMG_TYPE = "gif,jpg,jpeg,png,bmp";
	// 图片上传大小单位M
	private String IMG_SIZE = "1";
	@Resource
	private ArticleService articleService;
	@Resource
	private CatalogService catalogService;

	@RequestMapping({ "/toAdd" })
	public String toAdd(Model model, ArticleInfo articleInfo) {
		Catalog catalog = new Catalog();
		model.addAttribute("catalogList", this.catalogService.query(catalog));

		return "article/add";
	}

	@RequestMapping({ "/add" })
	public String add(Model model, ArticleInfo articleInfo) {
		this.articleService.save(articleInfo);
		return "redirect:/portal/index";
	}

	@RequestMapping({ "/publish" })
	public String publish(Model model, ArticleInfo articleInfo) {
		this.articleService.publish(articleInfo);
		return "redirect:/portal/index";
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
