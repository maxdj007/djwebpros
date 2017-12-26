package com.djwebpros.responses;

/**
 * 
 * @author DJ
 *
 */
public class SignUpResponseModel extends ResponseModel {
	
	private String UserHash;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserHash() {
		return UserHash;
	}

	public void setUserHash(String userHash) {
		UserHash = userHash;
	}

}
