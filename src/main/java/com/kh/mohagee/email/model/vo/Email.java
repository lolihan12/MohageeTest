package com.kh.mohagee.email.model.vo;

import org.springframework.stereotype.Component;

@Component
public class Email {
	
	private String email;
	private String key;
	
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Email(String email, String key) {
		super();
		this.email = email;
		this.key = key;
	}

	@Override
	public String toString() {
		return "Email [email=" + email + ", key=" + key + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
