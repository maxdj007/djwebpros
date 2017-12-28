package com.djwebpros.JWT;

import com.djwebpros.responses.JWTokenValidationModel;

/**
 * 
 * @author DJ
 *
 */
public interface JWTokenValidator {

	public  JWTokenValidationModel verifyJWT(String token);
	
}
