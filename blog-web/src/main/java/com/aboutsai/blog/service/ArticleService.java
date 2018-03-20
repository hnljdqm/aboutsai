package com.aboutsai.blog.service;

import java.util.List;

import com.aboutsai.blog.dto.ArticleInfoDto;
import com.aboutsai.blog.entity.ArticleContent;
import com.aboutsai.blog.entity.ArticleInfo;

public interface ArticleService {
	public void save(ArticleInfoDto articleInfoDto);

	public void publish(ArticleInfoDto articleInfoDto);

	public void updateHits(ArticleInfo articleInfoDto);

	public List<ArticleInfo> getMaxNewArticles();

	public ArticleInfo getArticleInfoById(String paramString);

	public ArticleContent getArticleContentById(String paramString);

	public List<ArticleInfo> getRecommendArticles();
	
	public List<ArticleInfo> query(ArticleInfoDto articleInfoDto);
}
