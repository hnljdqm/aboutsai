package com.aboutsai.blog.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aboutsai.blog.common.datadict.DataDictionary;
import com.aboutsai.blog.dao.ArticleContentDao;
import com.aboutsai.blog.dao.ArticleInfoDao;
import com.aboutsai.blog.dao.CatalogDao;
import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;
import com.aboutsai.blog.entity.Catalog;
import com.aboutsai.blog.service.ArticleService;
import com.aboutsai.framework.page.PageInfo;
import com.aboutsai.framework.util.BOHelper;
import com.aboutsai.framework.util.StringUtil;
import com.aboutsai.framework.util.WebUtil;
import com.github.pagehelper.PageHelper;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleInfoDao articleInfoDao;
	@Resource
	private ArticleContentDao articleContentDao;
	@Resource
	private CatalogDao catalogDao;

	@Override
	public PageInfo<ArticleInfo> query(ArticleInfo articleInfo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<ArticleInfo> page = new PageInfo<ArticleInfo>(articleInfoDao.query(articleInfo));
		page.setResults(page.getList());
		page.setList(null);
		return page;
	}
	
	public void save(ArticleInfo articleInfo) {
		saveArticle(articleInfo,
				Integer.valueOf(DataDictionary.Status.NO.getValue()));
	}

	public void publish(ArticleInfo articleInfo) {
		saveArticle(articleInfo,
				Integer.valueOf(DataDictionary.Status.YES.getValue()));
	}

	public void updateHits(ArticleInfo article) {
		article.setHits(Integer.valueOf(article.getHits().intValue() + 1));
		BOHelper.setModifyInfo(article);
		this.articleInfoDao.update(article);
	}

	private void saveArticle(ArticleInfo articleInfo,
			Integer publishStatus) {
		if (StringUtil.isEmpty(articleInfo.getId())) {
			Catalog catalog = this.catalogDao.getById(articleInfo
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
					.setArticleContent(articleInfo.getArticleContent());
			this.articleContentDao.insert(articleContent);
		} else {
			ArticleInfo _articleInfo = this.articleInfoDao.getById(articleInfo.getId());
			BeanUtils.copyProperties(_articleInfo, articleInfo, new String[] {
					"id", "createUserId", "createTime", "lastModifyUserId",
					"lastModifyTime", "deleteFlag" });
			BOHelper.setModifyInfo(articleInfo);
			handlePublishStatus(publishStatus, articleInfo);
			this.articleInfoDao.update(articleInfo);

			ArticleContent articleContent = this.articleContentDao
					.getById(articleInfo.getId());
			BOHelper.setModifyInfo(articleContent);
			articleContent
					.setArticleContent(articleInfo.getArticleContent());
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

	public ArticleInfo getArticleInfoById(String id) {
		return this.articleInfoDao.getById(id);
	}

	public ArticleContent getArticleContentById(String id) {
		return this.articleContentDao.getById(id);
	}
}
