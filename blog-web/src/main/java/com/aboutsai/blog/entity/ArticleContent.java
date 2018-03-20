package com.aboutsai.blog.entity;

import com.aboutsai.framework.base.entity.BaseEntity;

/**
 * 文章内容
 * 
 * @author hnljd
 *
 */
@SuppressWarnings("serial")
public class ArticleContent extends BaseEntity {
	private String articleContent;

	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
}
