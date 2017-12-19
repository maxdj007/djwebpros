package com.djwebpros.JWT;

import com.djwebpros.responses.JWTokenValidationModel;

public interface JWTokenValidator {

	public  JWTokenValidationModel verifyJWT(String token);
	public boolean isTokenStillValid(String token);
	
}
