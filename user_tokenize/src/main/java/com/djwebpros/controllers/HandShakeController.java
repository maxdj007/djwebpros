package com.djwebpros.controllers;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djwebpros.JWT.JWTokenCreator;
import com.djwebpros.commons.Constants;
import com.djwebpros.commons.PropertiesFileLoader;
import com.djwebpros.commons.Utility;
import com.djwebpros.models.User;
import com.djwebpros.responses.HandshakeResponseModel;
import com.djwebpros.responses.JWTMethodReturn;
import com.djwebpros.responses.UserResponseModel;
import com.djwebpros.service.UserService;

/**
 * 
 * @author DJ
 *
 */
@Controller
public class HandShakeController {
	
	private Logger logger = Logger.getLogger(HandShakeController.class);
	
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
	private JWTokenCreator jwtokenCreator;
	
	@Autowired 
	private Utility utility;
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody HandshakeResponseModel handShakeInitializer(@RequestBody String postPayload, @RequestHeader HttpHeaders headers){
		logger.info("Handshake inititalised");
		JSONObject postJSONData = new JSONObject(postPayload);
		HandshakeResponseModel handshakeResponse = new HandshakeResponseModel();
		if(utility.validateRequest(postJSONData, headers, Constants.REQUEST_TYPE_HANDSHAKE).isRequestValid()){
			logger.debug("Request validating creating token");
			JWTMethodReturn token = jwtokenCreator.createJWT(null);
			if(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS.equals(token.getStatus())){
				logger.debug("Setting response");
				handshakeResponse.setError(false);
				handshakeResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
				handshakeResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
				handshakeResponse.setToken(token.getToken());
			} else {
				logger.debug("Exception occured while generatin token : "+token.getMessage());
				handshakeResponse.setError(true);
				handshakeResponse.setMessage(property.getProperty("Token.Generation.Handshake.Error.Message"));
				handshakeResponse.setStatus(Constants.TOKEN_GENERATION_RETURNED_EXCEPTION);
			}
		} else {
			logger.debug("Errors found in the request - ");
			handshakeResponse.setError(true);
			handshakeResponse.setMessage(property.getProperty("Request.Validation.Error"));
			handshakeResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
		}
		logger.info("Returning response");
		return  handshakeResponse;
	}
	
	@RequestMapping(value = "/reHello", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody String handShakeInitializer1(@RequestBody String postPayload, @RequestHeader HttpHeaders headers){
		UserResponseModel newResponse = new UserResponseModel();
		int id =1;
		User user = userService.getUserById(id);
		newResponse.setError(false);
		newResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
		newResponse.setMessage("This is a test message");
		newResponse.setUser(user);
		return  "ksjhffhjklashflkasdhfkaskljfklafklashfkakf.asdfjklhasdklfasdklfhkl";
	}	
}
