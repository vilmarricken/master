package com.master.bov.entity;

import java.io.Serializable;

public class Conexao implements Serializable {

	private static final long serialVersionUID = -5392807751764901300L;

	private String url;

	private String user;

	private String password;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
