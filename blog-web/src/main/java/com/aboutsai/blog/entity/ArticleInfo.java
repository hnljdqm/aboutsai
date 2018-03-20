package com.aboutsai.blog.entity;

import com.aboutsai.framework.base.entity.BaseEntity;
import java.util.Date;

/**
 * 文章信息
 * @author hnljd
 *
 */
@SuppressWarnings("serial")
public class ArticleInfo extends BaseEntity {
	private Integer articleType;
	private String title;
	private String articleLabel;
	private String catalogId;
	private String catalogName;
	private Integer publishStatus;
	private Date publishTime;
	private String publishUserId;
	private Integer hits;
	private Integer isRecommend;
	
	public Integer getArticleType() {
		return this.articleType;
	}

	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticleLabel() {
		return this.articleLabel;
	}

	public void setArticleLabel(String articleLabel) {
		this.articleLabel = articleLabel;
	}

	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return this.catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Integer getPublishStatus() {
		return this.publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getPublishUserId() {
		return this.publishUserId;
	}

	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}

	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getIsRecommend() {
		return this.isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
}
