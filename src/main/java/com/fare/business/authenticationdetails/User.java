package com.fare.business.authenticationdetails;

import java.util.List;

public class User {

	private String username;
	private String password;
	private List<Role> role;

	public User(String userName, String password, List<Role> roles) {
		this.username = userName;
		this.password = password;
		this.role = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

}
