package com.djwebpros.commons;

import java.util.List;

import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.models.UserLevel;
import com.djwebpros.models.UserType;

public class MethodCallReturn {
	
	private String status;
	private String message;
	private String error;
	private User user;
	private List<User> users;
	private Token token;
	private List<Token> tokens;
	private UserType userType;
	private UserLevel userLevel;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public UserLevel getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
