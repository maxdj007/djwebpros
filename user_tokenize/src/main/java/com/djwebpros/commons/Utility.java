package com.djwebpros.commons;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONException;
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
	
	private Logger logger = Logger.getLogger(Utility.class);
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
	
	public boolean UserHashVerifier(String userHash){
		//TODO: user hashing algo
		return true;
	}
	
	public Date getTokenExpiration(Date date){
		logger.info("Returning token expiration time");
		Date expirationDate = null;
		date.getTime();
		expirationDate = new Date(date.getTime()+Long.parseLong(property.getProperty("Token.Expiration_Time_In_Miliseconds")));
		return expirationDate;
	}
	
	@SuppressWarnings("unchecked")
	public RequestValidationModel validateRequest(JSONObject postData, HttpHeaders httpHeaders, String requestType){
		logger.info("Starting the request validations and performing header validation before passing to factory");
		RequestValidationModel validationResponse = new RequestValidationModel();
		validationResponse.setError(false);
		try{
		if(!Constants.REQUEST_TYPE_HANDSHAKE.equals(requestType)){
			if(httpHeaders.getFirst(Constants.POST_DATA_FIELD_TOKEN) == null){
				logger.debug("no token found in request");
				validationResponse.setError(true);
				validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
				validationResponse.setMessage(property.getProperty("Request.Validation.Error.Null.Token"));
				validationResponse.setRequestValid(false);
				return validationResponse;
			}
			if(!jwtokenValidator.verifyJWT(httpHeaders.getFirst(Constants.POST_DATA_FIELD_TOKEN)).isTokenValid()){
				logger.debug("invalid token found in request");
				validationResponse.setError(true);
				validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
				validationResponse.setMessage(property.getProperty("Request.Validation.Error.Invalid.Token"));
				validationResponse.setRequestValid(false);
				return validationResponse;
			}
			if(!jwtokenValidator.isTokenStillValid(httpHeaders.getFirst(Constants.POST_DATA_FIELD_TOKEN)) && !Constants.REQUEST_TYPE_RE_HANDSHAKE.equals(requestType)){
				logger.debug("expired token found in request");
				validationResponse.setError(true);
				validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
				validationResponse.setMessage(property.getProperty("Request.Validation.Error.Expired.Token"));
				validationResponse.setRequestValid(false);
				return validationResponse;
			}
		}
		JSONObject errorJson = new JSONObject();
		logger.debug("Calling Validation Factory");
		ValidationFactory.getInstance().performRequestValidation(errorJson, requestType, postData);
		if(errorJson.length() == 0){
			logger.debug("No errors found in the request");
			validationResponse.setError(false);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			validationResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
			validationResponse.setRequestValid(true);
			validationResponse.setErrorsInRequest(null);
		} else if(errorJson.keySet().contains(Constants.EXCEPTION_OCCURED_DURING_METHOD_CALL) && errorJson.get(Constants.EXCEPTION_OCCURED_DURING_METHOD_CALL) != null){
			logger.debug("Exception occcured during the validation :"+errorJson.get(Constants.EXCEPTION_OCCURED_DURING_METHOD_CALL));
			validationResponse.setErrorsInRequest(validationResponse.getErrorsInRequest()+" + "+errorJson.getString(Constants.EXCEPTION_OCCURED_DURING_METHOD_CALL));
			validationResponse.setError(true);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_REQUEST_VALIDTION_FAILURE);
			validationResponse.setMessage(property.getProperty("Request.Validation.Error.Exception.Message"));
			validationResponse.setRequestValid(false);
			logger.debug("Errors in request : "+validationResponse.getErrorsInRequest());
		} else {
			Set<String> newOne = errorJson.keySet();
			for(String key : newOne){
				validationResponse.setErrorsInRequest(validationResponse.getErrorsInRequest()+" + "+errorJson.getString(key));
			}
			logger.debug("Errors found in the request :"+validationResponse.getErrorsInRequest());
			validationResponse.setError(true);
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_REQUEST_VALIDTION_FAILURE);
			validationResponse.setMessage(property.getProperty("Request.Validation.Error.Standard.Message"));
			validationResponse.setRequestValid(false);
			logger.debug("Errors in request : "+validationResponse.getErrorsInRequest());
		}
		} catch (JSONException jsonEception){
			logger.error("Errors found in the request :"+jsonEception.getMessage());
			validationResponse.setError(true);
			validationResponse.setErrorsInRequest(jsonEception.getMessage());
			validationResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_REQUEST_VALIDTION_FAILURE);
			validationResponse.setMessage(property.getProperty("Request.Validation.Error.Standard.Message"));
			validationResponse.setRequestValid(false);
		}
		return validationResponse;
	}
	
}
