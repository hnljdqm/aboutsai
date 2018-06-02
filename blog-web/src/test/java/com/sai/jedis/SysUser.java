package com.sai.jedis;

import java.io.Serializable;

public class SysUser implements Serializable {
	private String userName;
	private String passWord;

	public SysUser(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}

	public SysUser() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
