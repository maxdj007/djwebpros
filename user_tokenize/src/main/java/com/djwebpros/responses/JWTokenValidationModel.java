package com.djwebpros.responses;

public class JWTokenValidationModel extends ResponseModel {

	private boolean tokenValid = false;

	public boolean isTokenValid() {
		return tokenValid;
	}

	public void setTokenValid(boolean tokenValid) {
		this.tokenValid = tokenValid;
	}
	
}