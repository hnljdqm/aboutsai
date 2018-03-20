package com.aboutsai.blog.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.aboutsai.blog.common.datadict.DataDictionary;
import com.aboutsai.blog.dao.ArticleContentDao;
import com.aboutsai.blog.dao.ArticleInfoDao;
import com.aboutsai.blog.dao.CatalogDao;
import com.aboutsai.blog.dto.ArticleInfoDto;
import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.framework.util.BOHelper;
import com.aboutsai.framework.util.StringUtil;
import com.aboutsai.framework.util.WebUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleInfoDao articleInfoDao;
	@Resource
	private ArticleContentDao articleContentDao;
	@Resource
	private CatalogDao catalogDao;

	public List<ArticleInfo> query(ArticleInfoDto articleInfoDto) {
		return articleInfoDao.query(articleInfoDto);
	}
	
	public void save(ArticleInfoDto articleInfoDto) {
		saveArticle(articleInfoDto,
				Integer.valueOf(DataDictionary.Status.NO.getValue()));
	}

	public void publish(ArticleInfoDto articleInfoDto) {
		saveArticle(articleInfoDto,
				Integer.valueOf(DataDictionary.Status.YES.getValue()));
	}

	public void updateHits(ArticleInfo article) {
		article.setHits(Integer.valueOf(article.getHits().intValue() + 1));
		BOHelper.setModifyInfo(article);
		this.articleInfoDao.update(article);
	}

	private void saveArticle(ArticleInfoDto articleInfoDto,
			Integer publishStatus) {
		if (StringUtil.isEmpty(articleInfoDto.getId())) {
			ArticleInfo articleInfo = new ArticleInfo();
			BeanUtils.copyProperties(articleInfoDto, articleInfo);
			Catalog catalog = this.catalogDao.getById(articleInfoDto
					.getCatalogId());
			articleInfo.setCatalogName(catalog.getName());
			handlePublishStatus(publishStatus, articleInfo);
			articleInfo.setHits(Integer.valueOf(0));
			BOHelper.setCreateInfo(articleInfo);
			this.articleInfoDao.insert(articleInfo);

			ArticleContent articleContent = new ArticleContent();
			BOHelper.setCreateInfo(articleContent);
			articleContent.setId(articleInfo.getId());
			articleContent
					.setArticleContent(articleInfoDto.getArticleContent());
			this.articleContentDao.insert(articleContent);
		} else {
			ArticleInfo articleInfo = this.articleInfoDao
					.getById(articleInfoDto.getId());
			BeanUtils.copyProperties(articleInfoDto, articleInfo, new String[] {
					"id", "createUserId", "createTime", "lastModifyUserId",
					"lastModifyTime", "deleteFlag" });
			BOHelper.setModifyInfo(articleInfo);
			handlePublishStatus(publishStatus, articleInfo);
			this.articleInfoDao.update(articleInfo);

			ArticleContent articleContent = this.articleContentDao
					.getById(articleInfoDto.getId());
			BOHelper.setModifyInfo(articleContent);
			articleContent
					.setArticleContent(articleInfoDto.getArticleContent());
			this.articleContentDao.update(articleContent);
		}
	}

	private void handlePublishStatus(Integer publishStatus,
			ArticleInfo articleInfo) {
		articleInfo.setPublishStatus(publishStatus);
		if (publishStatus.intValue() == DataDictionary.Status.YES.getValue()) {
			articleInfo.setPublishUserId(WebUtil.getUserId());
			articleInfo.setPublishTime(new Date());
		}
	}

	public List<ArticleInfo> getMaxNewArticles() {
		return this.articleInfoDao.getMaxNewArticles();
	}

	public ArticleInfo getArticleInfoById(String id) {
		return this.articleInfoDao.getById(id);
	}

	public ArticleContent getArticleContentById(String id) {
		return this.articleContentDao.getById(id);
	}

	public List<ArticleInfo> getRecommendArticles() {
		return this.articleInfoDao.getRecommendArticles();
	}
}
