package com.djwebpros.JWT;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.djwebpros.commons.Constants;
import com.djwebpros.commons.PropertiesFileLoader;
import com.djwebpros.commons.Utility;
import com.djwebpros.controllers.HandShakeController;
import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.responses.JWTMethodReturn;
import com.djwebpros.service.TokenService;

/**
 * 
 * @author DJ
 *
 */
@Service
public class JWTCreator implements JWTokenCreator {
	
	private Logger logger = Logger.getLogger(HandShakeController.class);

	/**
	 * Properties file loader
	 */
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();

	/**
	 * Property
	 */
	protected Properties property = propertiesLoader.getMiscProperties();

	/*@Autowired
	private UserService userService;*/

	@Autowired
	private TokenService tokenService;

	@Autowired
	private Utility utility;

	public String createToken() throws UnsupportedEncodingException, JWTCreationException {
		String token = null;
		Builder tokenBuilder = null;
		Date tokenCreateDate = new Date();
		Algorithm algorithm = Algorithm.HMAC256(property.getProperty("JWT.Secret.Key"));
		tokenBuilder = JWT.create().withIssuer(property.getProperty("JWT.Secret.author")).withIssuedAt(tokenCreateDate)
				.withExpiresAt(utility.getTokenExpiration(tokenCreateDate))
				.withAudience(Constants.JWT_AUDIENCE_TYPE_GUEST);
		token = tokenBuilder.sign(algorithm);
		return token;
	}

	public String createToken(User user) throws UnsupportedEncodingException, JWTCreationException {
		String token = null;
		Builder tokenBuilder = null;
		Date tokenCreateDate = new Date();
		Algorithm algorithm = Algorithm.HMAC256(property.getProperty("JWT.Secret.Key"));
		tokenBuilder = JWT.create().withIssuer(property.getProperty("JWT.Secret.author")).withIssuedAt(tokenCreateDate)
				.withExpiresAt(utility.getTokenExpiration(tokenCreateDate))
				.withClaim(Constants.POST_DATA_FIELD_USER_HASH, user.getUserHash())
				.withClaim(Constants.POST_DATA_FIELD_FIRST_NAME, user.getFirstName())
				.withClaim(Constants.POST_DATA_FIELD_LAST_NAME, user.getLastName())
				.withAudience(Constants.JWT_AUDIENCE_TYPE_USER);
		token = tokenBuilder.sign(algorithm);
		return token;
	}

	public JWTMethodReturn createJWT(User user) {
		logger.info("Starting to create token as required");
		JWTMethodReturn returnObject = new JWTMethodReturn();
		String token = null;
		try {
			if (user != null) {
				logger.info("creating token with user");
				token = this.createToken(user);
				System.out.println(token);
				Token newToken = new Token();
				newToken.setLoggedIn(1);
				newToken.setToken(token);
				newToken.setAvailDate(new Timestamp(new Date().getTime()));
				newToken.setUser_hash(user.getUserHash());
				newToken.setValid(true);
				tokenService.addToken(newToken);
				returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
				returnObject.setError(false);
				returnObject.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
				returnObject.setToken(token);
			} else {
				logger.info("creating token with user null i.e the handshake token");
				token = this.createToken();
				System.out.println(token);
				Token newToken = new Token();
				newToken.setLoggedIn(0);
				newToken.setToken(token);
				newToken.setAvailDate(new Timestamp(new Date().getTime()));
				newToken.setUser_hash(null);
				newToken.setValid(true);
				tokenService.addToken(newToken);
				returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
				returnObject.setError(false);
				returnObject.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
				returnObject.setToken(token);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException exception occured : with message: "+e.getMessage());
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR);
			returnObject.setError(true);
			returnObject.setMessage(e.getMessage());
		} catch (JWTCreationException e) {
			logger.error("JWTCreationException exception occured : with message: "+e.getMessage());
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR);
			returnObject.setError(true);
			returnObject.setMessage(e.getMessage());
		} catch (RuntimeException rException) {
			logger.error("RuntimeException exception occured : with message: "+rException.getMessage());
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR);
			returnObject.setError(true);
			returnObject.setMessage(rException.getMessage());
		} catch (Exception exception){
			logger.error("exception occured : with message: "+exception.getMessage());
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR);
			returnObject.setError(true);
			returnObject.setMessage(exception.getMessage());
		}
		
		return returnObject;
	}

	public JWTMethodReturn createJWT(User user, Token token) {
		// TODO Auto-generated method stub
		return null;
	}
}
