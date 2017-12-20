package com.djwebpros.commons;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.djwebpros.JWT.JWTokenValidator;
import com.djwebpros.models.User;
import com.djwebpros.responses.RequestValidationModel;
import com.djwebpros.validator.ValidationFactory;

/**
 * 
 * @author DJ
 *
 */
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
		expirationDate = new Date(date.getTime()+Long.parseLong(property.getProperty("Token.Expiration_Time_In_Miliseconds")));
		return expirationDate;
	}
	
	@SuppressWarnings("unchecked")
	public RequestValidationModel validateRequest(JSONObject postData, HttpHeaders httpHeaders, String requestType){
		RequestValidationModel validationResponse = new RequestValidationModel();
		validationResponse.setError(false);
		if(httpHeaders.getFirst(Constants.POST_DATA_FIELD_TOKEN) == null){
			validationResponse.setError(true);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
			validationResponse.setMessage(property.getProperty("Request.Validation.Error.Null.Token"));
			return validationResponse;
		}
		if(!jwtokenValidator.verifyJWT(postData.getString(Constants.POST_DATA_FIELD_TOKEN)).isTokenValid()){
			validationResponse.setError(true);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
			validationResponse.setMessage(property.getProperty("Request.Validation.Error.Invalid.Token"));
			return validationResponse;
		}
		if(!jwtokenValidator.isTokenStillValid(postData.getString(Constants.POST_DATA_FIELD_TOKEN))){
			validationResponse.setError(true);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
			validationResponse.setMessage(property.getProperty("Request.Validation.Error.Expired.Token"));
			return validationResponse;
		}
		JSONObject errorJson = new JSONObject();
		ValidationFactory.getInstance().performRequestValidation(errorJson, requestType, postData);
		if(errorJson.length() == 0){
			validationResponse.setError(false);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			validationResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
			validationResponse.setRequestValid(true);
			validationResponse.setErrorsInRequest(null);
		} else {
			Set<String> newOne = errorJson.keySet();
			for(String key : newOne){
				validationResponse.setErrorsInRequest(validationResponse.getErrorsInRequest()+" + "+errorJson.getString(key));
			}
			validationResponse.setError(true);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_REQUEST_VALIDTION_FAILURE);
			validationResponse.setMessage(property.getProperty("Request.Validation.Error.Standard.Message"));
			validationResponse.setRequestValid(true);
			
		}
		return validationResponse;
	}
	
}
