package com.djwebpros.JWT;

public interface JWTokenValidator {

	public boolean verifyJWT(String token);
	public boolean isTokenStillValid(String token);
	
}
