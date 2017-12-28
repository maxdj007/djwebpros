package com.djwebpros.controllers;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONException;
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
import com.djwebpros.responses.JWTMethodReturn;
import com.djwebpros.responses.LogOutResponseModel;
import com.djwebpros.responses.LoginResponseModel;
import com.djwebpros.service.UserService;

/**
 * 
 * @author DJ
 *
 */
@Controller
public class UserCheckController {
	
	private Logger logger = Logger.getLogger(UserSignUpController.class);
	
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
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody LoginResponseModel loginController(@RequestBody String postPayload, @RequestHeader HttpHeaders headers){
		logger.info("Login inititalised");
		JSONObject postJSONData = new JSONObject(postPayload);
		LoginResponseModel loginResponse = new LoginResponseModel();
		if(utility.validateRequest(postJSONData, headers, Constants.REQUEST_TYPE_LOGIN).isRequestValid()){
			logger.debug("Request validating creating token");
			try{
				User user = new User();
				user.setEmailId(postJSONData.getString(Constants.POST_DATA_FIELD_EMAIL_ID));
				user.setPassHash(postJSONData.getString(Constants.POST_DATA_FIELD_PASS_HASH));
				loginResponse = loginUser(user);
				loginResponse = (Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS.equals(loginResponse.getStatus())) ? createLoggedInToken(loginResponse.getUser(), loginResponse) : loginResponse ;
				
			} catch (JSONException e){
				logger.error("Error occured while parsing form data. With Message : "+e.getMessage());
				loginResponse.setError(true);
				loginResponse.setMessage(property.getProperty("Json.Parse.Exception.User.Setting"));
				loginResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_EXCEPTION);
			} 
		} else {
			loginResponse.setError(true);
			loginResponse.setMessage(property.getProperty("Request.Validation.Error"));
			loginResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
		}
		logger.info("Returning response");
		return  loginResponse;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/isLoggedIn", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody String isLoggedIn(@RequestBody String postPayload){
		//TODO : Add the mechanism for login check
		JSONObject postJSONData = new JSONObject(postPayload);
		return  "ksjhffhjklashflkasdhfkaskljfklafklashfkakf.asdfjklhasdklfasdklfhkl";
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody LogOutResponseModel logOutController(@RequestBody String postPayload, @RequestHeader HttpHeaders headers){
		logger.info("logout inititalised");
		JSONObject postJSONData = new JSONObject(postPayload);
		LogOutResponseModel logoutResponse = new LogOutResponseModel();
		if(utility.validateRequest(postJSONData, headers, Constants.REQUEST_TYPE_LOGOUT).isRequestValid()){
			try{
				User user = new User();
				user.setEmailId(postJSONData.getString(Constants.POST_DATA_FIELD_EMAIL_ID));
				logoutResponse = logOutUser(user);				
			} catch (JSONException e){
				logger.error("Error occured while parsing form data. With Message : "+e.getMessage());
				logoutResponse.setError(true);
				logoutResponse.setMessage(property.getProperty("Json.Parse.Exception.User.Setting"));
				logoutResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_EXCEPTION);
			} 
		} else {
			logoutResponse.setError(true);
			logoutResponse.setMessage(property.getProperty("Request.Validation.Error"));
			logoutResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
		}
		logger.info("Returning response");
		return  logoutResponse;
	}
	
	private LoginResponseModel createLoggedInToken(User user, LoginResponseModel loginResponse){
		JWTMethodReturn token = jwtokenCreator.createJWT(user);
		if(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS.equals(token.getStatus())){
			logger.debug("Setting response");
			loginResponse.setError(false);
			loginResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
			loginResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			loginResponse.setToken(token.getToken());
		} else {
			logger.debug("Could not generate token : "+token.getMessage());
			loginResponse.setError(true);
			loginResponse.setUser(null);
			loginResponse.setMessage(property.getProperty("Token.Generation.SignUporlogin.Error.Message"));
			loginResponse.setStatus(Constants.TOKEN_GENERATION_RETURNED_EXCEPTION);
		}
		return loginResponse;
	}
	
	private LoginResponseModel loginUser(User user){
		LoginResponseModel loginResponse = new LoginResponseModel();
		try{
			loginResponse = userService.userLoginCheck(user);
			if(!Constants.METHOD_CALL_RETURN_STATUS_LOGIN_FAILURE_NO_USER.equals(loginResponse.getStatus())){
				loginResponse.getUser().setPassHash(null);
				loginResponse.setLoginStatus(Constants.LOGIN_STATUS_LOGGED_IN);
				return loginResponse;
			} 
			logger.debug("Could not log in......");
			loginResponse.setError(true);
			loginResponse.setMessage(property.getProperty("Login.Error.Not.Able.To.Login"));
			loginResponse.setStatus(Constants.TOKEN_GENERATION_RETURNED_EXCEPTION);
		} catch (RuntimeException runTimeException){
			logger.error("Error Occured during adding to database. With Message : "+runTimeException.getMessage());
			loginResponse.setError(true);
			loginResponse.setMessage(property.getProperty("Database.RelatedIssue.Error.Message"));
			loginResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_HIBERNATE_EXCEPTION);
		}
		return loginResponse;
	}
	
	private LogOutResponseModel logOutUser(User user){
		LogOutResponseModel logOutResponse = new LogOutResponseModel();
		try{
			user = userService.getUserByEmailId(user);
			user.setLoggedIn(false);
			userService.updateUser(user);
			JWTMethodReturn token = jwtokenCreator.createJWT(null);
			if(!Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR.equals(token.getStatus())){
				logOutResponse.setError(false);
				logOutResponse.setLogOutSuccessful(true);
				logOutResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
				logOutResponse.setToken(token.getToken());
				return logOutResponse;
			} 
			logger.debug("Could not create token");
			logOutResponse.setError(true);
			logOutResponse.setLogOutSuccessful(true);
			logOutResponse.setMessage(property.getProperty("LogOut.Error.Not.Able.To.Create.Token"));
			logOutResponse.setStatus(Constants.TOKEN_GENERATION_RETURNED_EXCEPTION);
		} catch (RuntimeException runTimeException){
			logger.error("Error Occured during adding to database. With Message : "+runTimeException.getMessage());
			logOutResponse.setError(true);
			logOutResponse.setLogOutSuccessful(false);
			logOutResponse.setMessage(property.getProperty("Database.RelatedIssue.Error.Message"));
			logOutResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_HIBERNATE_EXCEPTION);
		}
		return logOutResponse;
	}

}
