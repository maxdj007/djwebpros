package com.djwebpros.responses;

/**
 * 
 * @author DJ
 *
 */
public class SignUpResponseModel extends ResponseModel {
	
	private String UserHash;

	public String getUserHash() {
		return UserHash;
	}

	public void setUserHash(String userHash) {
		UserHash = userHash;
	}

}
