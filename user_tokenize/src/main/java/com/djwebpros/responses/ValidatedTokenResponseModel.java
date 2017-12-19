package com.djwebpros.responses;

public class ValidatedTokenResponseModel extends ResponseModel {
	
	private boolean tokenValid;

	public boolean isTokenValidation() {
		return tokenValid;
	}

	public void setTokenValidation(boolean tokenValidation) {
		this.tokenValid = tokenValidation;
	}

}
