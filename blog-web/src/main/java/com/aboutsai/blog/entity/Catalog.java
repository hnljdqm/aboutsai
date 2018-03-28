package com.aboutsai.blog.entity;

import com.aboutsai.framework.base.entity.BaseEntity;

/**
 * 文章分类
 * 
 * @author hnljd
 *
 */
@SuppressWarnings("serial")
public class Catalog extends BaseEntity {
	private String code;
	private String name;
	private String parentId;
	
	//非持久化字段
	// 分类的文章数
	private Integer counts;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

}
