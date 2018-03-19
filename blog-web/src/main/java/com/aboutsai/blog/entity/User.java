package com.aboutsai.blog.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3465577961626331421L;
	
	private String id;
	private String name;
	private String email;
    private String passwrod;

	public User() {
		super();
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}


	public User(String id, String name, String email,String passwrod) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.passwrod = passwrod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

}