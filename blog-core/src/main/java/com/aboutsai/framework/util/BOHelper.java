package com.aboutsai.framework.util;

import java.util.Date;
import java.util.UUID;

import com.aboutsai.framework.base.entity.BaseEntity;

public class BOHelper {
	public static void setCreateInfo(BaseEntity be) {
		be.setId(UUID.randomUUID().toString());
		be.setCreateTime(new Date());
		be.setCreateUserId(WebUtil.getUserId());
		be.setLastModifyTime(new Date());
		be.setLastModifyUserId(WebUtil.getUserId());
		be.setDeleteFlag(Integer.valueOf(0));
	}

	public static void setModifyInfo(BaseEntity be) {
		be.setLastModifyTime(new Date());
		be.setLastModifyUserId(WebUtil.getUserId());
	}
}
