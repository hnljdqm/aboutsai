package com.aboutsai.blog.dto;

import com.aboutsai.blog.entity.ArticleInfo;

@SuppressWarnings("serial")
public class ArticleInfoDto extends ArticleInfo {
	private String articleContent;

	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
}
