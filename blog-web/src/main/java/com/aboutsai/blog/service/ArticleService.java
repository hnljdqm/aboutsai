package com.aboutsai.blog.service;

import com.aboutsai.blog.common.page.PageInfo;
import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;

public interface ArticleService {
	public void save(ArticleInfo articleInfo);

	public void publish(ArticleInfo articleInfo);

	public void updateHits(ArticleInfo articleInfo);

	public ArticleInfo getArticleInfoById(String paramString);

	public ArticleContent getArticleContentById(String paramString);

	public PageInfo<ArticleInfo> query(ArticleInfo articleInfo, int pageNum, int pageSize);
}
