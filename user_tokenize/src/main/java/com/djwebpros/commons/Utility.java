package com.djwebpros.commons;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.djwebpros.models.User;
import com.djwebpros.responses.ResponseModel;

@Component
public class Utility {
	
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
	
	public ResponseModel validateRequest(JSONObject postData, HttpHeaders httpHeaders){
		ResponseModel validationResponse = new ResponseModel();
		
		return validationResponse;
	}
	
}
