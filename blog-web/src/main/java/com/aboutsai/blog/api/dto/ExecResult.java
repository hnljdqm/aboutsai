package com.aboutsai.blog.api.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ExecResult implements Serializable {
	/** 成功标记 */
	private boolean success;
	/** 成功标记 */
	private String errCode;
	/** 返回消息 */
	private String message;

	public ExecResult(boolean success, String errCode, String message) {
		super();
		this.success = success;
		this.errCode = errCode;
		this.message = message;
	}

	public ExecResult() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
