package com.djwebpros.JWT;

/**
 * 
 * @author DJ
 *
 */
import org.springframework.stereotype.Service;

import com.djwebpros.commons.Constants;
import com.djwebpros.responses.JWTokenValidationModel;

@Service
public class JWTokenValidatorImpl implements JWTokenValidator {

	public JWTokenValidationModel verifyJWT(String token) {
		// TODO Auto-generated method stub
		JWTokenValidationModel newResponse = new JWTokenValidationModel();
		newResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
		newResponse.setError(false);
		newResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
		newResponse.setTokenValid(true);
		return newResponse;
	}

	public boolean isTokenStillValid(String token) {
		// TODO Auto-generated method stub
		return true;
	}

}
