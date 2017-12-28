package com.djwebpros.responses;

public class LogOutResponseModel extends ResponseModel {
	
	private boolean logOutSuccessful;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isLogOutSuccessful() {
		return logOutSuccessful;
	}

	public void setLogOutSuccessful(boolean logOutSuccessful) {
		this.logOutSuccessful = logOutSuccessful;
	}
	
	

}
