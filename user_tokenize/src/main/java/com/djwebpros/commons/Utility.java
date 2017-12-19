package com.djwebpros.commons;

import java.util.Date;
import java.util.Properties;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.djwebpros.JWT.JWTokenValidator;
import com.djwebpros.models.User;
import com.djwebpros.responses.JWTokenValidationModel;

@Component
public class Utility {
	
	/**
	 * Properties file loader
	 */
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();

	/**
	 * Property
	 */
	protected Properties property = propertiesLoader.getMiscProperties();
	
	@Autowired
	private JWTokenValidator jwtokenValidator;
	
	public String passwordHasher(String password){
		//TODO: password hashing algo
		return null;
	}
	
	public String UserHashGenerator(User user){
		//TODO: user hashing algo
		return null;
	}
	
	public Date getTokenExpiration(Date date){
		Date expirationDate = null;
		date.getTime();
		expirationDate = new Date(date.getTime()+Constants.TOKEN_EXPIRATION_TIME_IN_MILISECONDS);
		return expirationDate;
	}
	
	public JWTokenValidationModel validateRequest(JSONObject postData, HttpHeaders httpHeaders){
		JWTokenValidationModel validationResponse = new JWTokenValidationModel();
		validationResponse.setError(false);
		if(httpHeaders.getFirst(Constants.POST_DATA_FIELD_TOKEN) == null){
			validationResponse.setTokenValid(true);
		}
		if(!jwtokenValidator.verifyJWT(postData.getString(Constants.POST_DATA_FIELD_TOKEN)).isTokenValid()){
			validationResponse.setTokenValid(true);
		}
		if(!jwtokenValidator.isTokenStillValid(postData.getString(Constants.POST_DATA_FIELD_TOKEN))){
			
		}
		
		return validationResponse;
	}
	
}
