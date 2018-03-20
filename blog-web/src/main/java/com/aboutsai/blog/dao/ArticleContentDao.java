package com.aboutsai.blog.dao;

import org.apache.ibatis.annotations.Param;

import com.aboutsai.blog.entity.ArticleContent;

public interface ArticleContentDao {
	public ArticleContent getById(@Param("id") String id);

	public void insert(ArticleContent ac);

	public void update(ArticleContent ac);
}
