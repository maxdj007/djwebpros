package com.djwebpros.controllers;

import java.util.Properties;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djwebpros.JWT.JWTokenValidator;
import com.djwebpros.commons.Constants;
import com.djwebpros.commons.PropertiesFileLoader;
import com.djwebpros.commons.Utility;
import com.djwebpros.responses.ValidatedTokenResponseModel;

public class ValidateTokenController {
	
	/**
	 * Properties file loader
	 */
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();

	/**
	 * Property
	 */
	protected Properties property = propertiesLoader.getMiscProperties();

	@Autowired
	private Utility utility;
	
	@Autowired
	private JWTokenValidator jwTokenValidator;
	
	@RequestMapping(value = "/validateToken", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody ValidatedTokenResponseModel signUp(@RequestBody String postPayload, @RequestHeader HttpHeaders headers){
		JSONObject postJSONData = new JSONObject(postPayload);
		ValidatedTokenResponseModel validatedTokenResponseModel = new ValidatedTokenResponseModel();
		if(utility.validateRequest(postJSONData, headers).isError()){
			boolean isValid = jwTokenValidator.isTokenStillValid(postJSONData.getString(Constants.POST_DATA_FIELD_TOKEN));
			validatedTokenResponseModel.setError(false);
			validatedTokenResponseModel.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
			validatedTokenResponseModel.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			validatedTokenResponseModel.setTokenValidation(isValid);
		} else {
			validatedTokenResponseModel.setError(true);
			validatedTokenResponseModel.setMessage(property.getProperty("Request.Validation.Error"));
			validatedTokenResponseModel.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
		}
		return  validatedTokenResponseModel;
	}

}
