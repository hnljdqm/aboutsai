package com.aboutsai.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aboutsai.blog.entity.ArticleInfo;

public interface ArticleInfoDao {
	public ArticleInfo getById(@Param("id") String id);

	public void insert(ArticleInfo articleInfoDto);

	public void update(ArticleInfo articleInfoDto);

	public List<ArticleInfo> query(ArticleInfo articleInfo);
}
