package com.djwebpros.JWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.djwebpros.commons.Constants;
import com.djwebpros.commons.PropertiesFileLoader;
import com.djwebpros.commons.Utility;
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
		JWTMethodReturn returnObject = new JWTMethodReturn();
		String token = null;
		try {
			if (user != null) {
				token = this.createToken(user);
				System.out.println(token);
				Token newToken = new Token();
				newToken.setLoggedIn(1);
				newToken.setToken(token);
				newToken.setUser_hash(user.getUserHash());
				newToken.setValid(true);
				tokenService.addToken(newToken);
			} else {
				token = this.createToken();
				System.out.println(token);
				Token newToken = new Token();
				newToken.setLoggedIn(0);
				newToken.setToken(token);
				newToken.setUser_hash(null);
				newToken.setValid(true);
				tokenService.addToken(newToken);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWTCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException rException) {
			// TODO: handle exception
		} catch (Exception exception){
			//TODO: logg it
		}
		
		return returnObject;
	}

	public JWTMethodReturn createJWT(User user, Token token) {
		// TODO Auto-generated method stub
		return null;
	}
}
