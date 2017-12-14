package com.djwebpros.JWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.djwebpros.commons.PropertiesFileLoader;
import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.service.TokenService;
import com.djwebpros.service.UserService;

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

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	public static String coolMethod(){
		String token = null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    token = JWT.create()
		        .withIssuer("auth0")
		        .sign(algorithm);
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
		} catch (JWTCreationException exception1){
		    //Invalid Signing configuration / Couldn't convert Claims.
		}
		return token;
	}

	public String createJWT(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createJWT(User user, Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verifyJWT(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public User getUserFromToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isTokenStillValid(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public Date getTokenCreationDate(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTimeToLivefromToken(String token) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
