package com.djwebpros.JWT;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.djwebpros.commons.Constants;
import com.djwebpros.commons.PropertiesFileLoader;
import com.djwebpros.responses.JWTokenValidationModel;

/**
 * 
 * @author DJ
 *
 */
@Service
public class JWTokenValidatorImpl implements JWTokenValidator {
	
	private Logger logger = Logger.getLogger(JWTokenValidatorImpl.class);

	/**
	 * Properties file loader
	 */
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();

	/**
	 * Property
	 */
	protected Properties property = propertiesLoader.getMiscProperties();

	public JWTokenValidationModel verifyJWT(String token) {
		JWTokenValidationModel newResponse = new JWTokenValidationModel();
		try {
		    Algorithm algorithm = Algorithm.HMAC256(property.getProperty("JWT.Secret.Key"));
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer(property.getProperty("JWT.Secret.author"))
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    logger.info("Token verified with Issuer : " + jwt.getIssuer() + " And Audience : " + jwt.getAudience());
		    newResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
			newResponse.setError(false);
			newResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			newResponse.setTokenValid(true);
			newResponse.setTokenExpired(false);
		} catch (UnsupportedEncodingException exception){
			logger.error(Constants.STANDARD_EXCEPTION_MESSAGE+" : "+"UnsupportedEncodingException : "+ exception.getMessage());
			newResponse.setMessage(Constants.STANDARD_EXCEPTION_MESSAGE+" : "+"UnsupportedEncodingException : "+ exception.getMessage());
			newResponse.setError(true);
			newResponse.setStatus(Constants.EXCEPTION_OCCURED_DURING_METHOD_CALL);
			newResponse.setTokenValid(false);
			newResponse.setTokenExpired(true);
		} catch (JWTVerificationException exception){
			logger.error("The token could not be verified. exception : " + exception.fillInStackTrace().getClass() + " Message : "+exception.getMessage());
			handleJWTVerificationException(exception, newResponse);
		}
		return newResponse;
	}

	private void handleJWTVerificationException(JWTVerificationException exception, JWTokenValidationModel newResponse){
		
		if(ExceptionUtils.indexOfThrowable(exception, TokenExpiredException.class) != -1){
			newResponse.setTokenExpired(true);
			newResponse.setTokenValid(false);
			newResponse.setError(false);
			newResponse.setMessage(property.getProperty("Token.Expired.Message"));
			newResponse.setStatus(Constants.TOKEN_EXPIRED);
		} else {
			newResponse.setTokenExpired(false);
			newResponse.setTokenValid(false);
			newResponse.setError(false);
			newResponse.setMessage(property.getProperty("Token.Invalid.Message"));
			newResponse.setStatus(Constants.TOKEN_EXPIRED);
		}
	}

}
