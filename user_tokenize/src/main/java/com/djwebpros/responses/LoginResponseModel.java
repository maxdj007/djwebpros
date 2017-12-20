package com.djwebpros.responses;

import java.util.Date;

import com.djwebpros.models.User;

/**
 * 
 * @author DJ
 *
 */
public class LoginResponseModel extends ResponseModel {

	private User user;
	private String token;
	private String loginStatus;
	private Date lastLogin;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
