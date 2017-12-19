package com.djwebpros.JWT;

import org.springframework.stereotype.Service;

import com.djwebpros.responses.JWTokenValidationModel;

@Service
public class JWTokenValidatorImpl implements JWTokenValidator {

	public JWTokenValidationModel verifyJWT(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isTokenStillValid(String token) {
		// TODO Auto-generated method stub
		return false;
	}

}
