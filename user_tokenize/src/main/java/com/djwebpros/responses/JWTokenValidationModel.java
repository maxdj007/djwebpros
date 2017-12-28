package com.djwebpros.responses;

/**
 * 
 * @author DJ
 *
 */
public class JWTokenValidationModel extends ResponseModel {

	private boolean tokenValid = false;
	private boolean tokenExpired;

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public boolean isTokenValid() {
		return tokenValid;
	}

	public void setTokenValid(boolean tokenValid) {
		this.tokenValid = tokenValid;
	}
	
}
