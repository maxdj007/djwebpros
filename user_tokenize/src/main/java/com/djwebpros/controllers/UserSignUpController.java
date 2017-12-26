package com.djwebpros.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.djwebpros.responses.SignUpResponseModel;
import com.djwebpros.service.UserLevelService;
import com.djwebpros.service.UserService;
import com.djwebpros.service.UserTypeService;

/**
 * 
 * @author DJ
 *
 */
@Controller
public class UserSignUpController {
	
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
	private UserLevelService userLevelService;
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private JWTokenCreator jwtokenCreator;
	
	@Autowired 
	private Utility utility;
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST, consumes = "text/plain")
	public @ResponseBody SignUpResponseModel signUp(@RequestBody String postPayload, @RequestHeader HttpHeaders headers){
		logger.info("Handshake inititalised");
		JSONObject postJSONData = new JSONObject(postPayload);
		SignUpResponseModel signUPResponse = new SignUpResponseModel();
		if(utility.validateRequest(postJSONData, headers, Constants.REQUEST_TYPE_SIGNUP).isRequestValid()){
			logger.debug("Request validating creating token");
			try{
				User user = setUserDetails(postJSONData);
				user.setUserHash(utility.UserHashGenerator(user));
				userService.addUser(user);
				JWTMethodReturn token = jwtokenCreator.createJWT(null);
				if(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS.equals(token.getStatus())){
					logger.debug("Setting response");
					signUPResponse.setError(false);
					signUPResponse.setMessage(Constants.STANDARD_SUCCESS_MESSAGE);
					signUPResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
					signUPResponse.setToken(token.getToken());
				} else {
					logger.debug("Could not generate token : "+token.getMessage());
					signUPResponse.setError(true);
					signUPResponse.setMessage(property.getProperty("Token.Generation.SignUporlogin.Error.Message"));
					signUPResponse.setStatus(Constants.TOKEN_GENERATION_RETURNED_EXCEPTION);
				}
			} catch (ParseException | JSONException e){
				logger.error("Error occured while parsing form data. With Message : "+e.getMessage());
				signUPResponse.setError(true);
				signUPResponse.setMessage(property.getProperty("Json.Parse.Exception.User.Setting"));
				signUPResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_EXCEPTION);
			} catch (RuntimeException runTimeException){
				logger.error("Error Occured during adding to database. With Message : "+runTimeException.getMessage());
				signUPResponse.setError(true);
				signUPResponse.setMessage(property.getProperty("Database.RelatedIssue.Error.Message"));
				signUPResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_HIBERNATE_EXCEPTION);
			}
		} else {
			signUPResponse.setError(true);
			signUPResponse.setMessage(property.getProperty("Request.Validation.Error"));
			signUPResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE);
		}
		logger.info("Returning response");
		return  signUPResponse;
	}
	
	private User setUserDetails(JSONObject postJSONData) throws JSONException, ParseException {
		User user = new User();
		logger.info("Setting all user data in a new User Object");
		user.setFirstName(postJSONData.getString(Constants.POST_DATA_FIELD_FIRST_NAME));
		user.setLastName(postJSONData.getString(Constants.POST_DATA_FIELD_LAST_NAME));
		user.setMiddleName(postJSONData.getString(Constants.POST_DATA_FIELD_MIDDLE_NAME));
		user.setEmailId(postJSONData.getString(Constants.POST_DATA_FIELD_EMAIL_ID));
		user.setDateOfBirth(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(postJSONData.getString(Constants.POST_DATA_FIELD_DATE_OF_BIRTH)).getTime()));
		user.setMobileNumber(postJSONData.getString(Constants.POST_DATA_FIELD_MOBILE_NUMBER));
		user.setNickName(postJSONData.getString(Constants.POST_DATA_FIELD_NICK_NAME));
		user.setPassHash(postJSONData.getString(Constants.POST_DATA_FIELD_PASS_HASH));
		user.setPic(postJSONData.getString(Constants.POST_DATA_FIELD_PICTURE));
		user.setUserLevel(userLevelService.getUserLevelById(Integer.parseInt(postJSONData.getString(Constants.POST_DATA_FIELD_USER_LEVEL))));
		user.setUserType(userTypeService.getUserTypeById(Integer.parseInt(postJSONData.getString(Constants.POST_DATA_FIELD_USER_TYPE))));
		logger.info("returning Set user data");		
		return user;
	}
}
