package com.djwebpros.commons;

/**
 * 
 * @author DJ
 *
 */
public class Constants {

	public static final String STANDARD_SUCCESS_MESSAGE = "The call was a success cheers!";
	
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS = "SUCCESS";
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_ERROR = "FAIL";
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE = "FALSE";
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE = "TRUE";
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_AUTHENTICATION_FAILURE = "REQUEST_AUTHENTICATION_FAILED";
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_REQUEST_VALIDTION_FAILURE = "REQUEST_VALIDATION_FAILED";
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_HIBERNATE_EXCEPTION = "HIBERNATE_EXCEPTION";
	public static final String METHOD_CALL_RETURN_STATUS_VALUE_EXCEPTION = "OTHER_EXCEPTION";
	public static final String METHOD_CALL_RETURN_STATUS_LOGIN_FAILURE_NO_USER = "USER_NOT_FOUND";
	
	public static final String TOKEN_GENERATION_RETURNED_EXCEPTION = "TOKEN_GENERATION_EXCEPTION";
	
	public static final String EXCEPTION_OCCURED_DURING_METHOD_CALL = "exception_occured";
	public static final String POST_DATA_FIELD_APPLICATION = "application";
	public static final String POST_DATA_FIELD_APPLICATION_ID = "application_id";
	public static final String POST_DATA_FIELD_VERIFYING_HASH = "verifying_hash";
	
	public static final String POST_DATA_FIELD_FIRST_NAME = "first_name";
	public static final String POST_DATA_FIELD_MIDDLE_NAME = "middle_name";
	public static final String POST_DATA_FIELD_LAST_NAME = "last_name";
	public static final String POST_DATA_FIELD_DATE_OF_BIRTH = "dob";
	public static final String POST_DATA_FIELD_EMAIL_ID = "email_id";
	public static final String POST_DATA_FIELD_MOBILE_NUMBER = "mobile_number";
	public static final String POST_DATA_FIELD_PASS_HASH = "pass_hash";
	public static final String POST_DATA_FIELD_SIGN_UP_TYPE = "sign_up_type";
	public static final String POST_DATA_FIELD_SIGN_UP_REQUESTOR = "sign_up_requestor";
	public static final String POST_DATA_FIELD_USER_TYPE = "user_type";
	public static final String POST_DATA_FIELD_USER_LEVEL = "user_level";
	public static final String POST_DATA_FIELD_TOKEN = "token";
	public static final String POST_DATA_FIELD_STATUS = "status";
	public static final String POST_DATA_FIELD_MESSAGE = "message";
	public static final String POST_DATA_FIELD_NICK_NAME = "nick_name";
	public static final String POST_DATA_FIELD_PICTURE = "picture";
	public static final String POST_DATA_FIELD_USER_HASH = "user_hash";
	public static final String POST_DATA_FIELD_IS_LOGGED_IN = "is_logged_in";
	
	public static final String JWT_AUDIENCE_TYPE_GUEST = "GUEST";
	public static final String JWT_AUDIENCE_TYPE_USER = "USER";
	
	public static final long TOKEN_EXPIRATION_TIME_IN_MILISECONDS = 28800000;
	
	public static final String USER_LEVEL_ADMIN = "ADMIN";
	public static final String USER_LEVEL_AUTHOR = "AUTHOR";
	public static final String USER_LEVEL_GUEST = "GUEST";
	
	public static final String REQUEST_TYPE_VALIDATE_TOKEN = "ValidateToken";
	public static final String REQUEST_TYPE_LOGIN = "Login";
	public static final String REQUEST_TYPE_LOGOUT = "LogOut";
	public static final String REQUEST_TYPE_SIGNUP = "SignUp";
	public static final String REQUEST_TYPE_IS_LOGGED_IN = "IsLoggedIn";
	public static final String REQUEST_TYPE_HANDSHAKE = "Handshake";
	public static final String REQUEST_TYPE_RE_HANDSHAKE = "ReHandshake";
	
	public static final String VALIDATION_PACKAGE_ABSOLUTE_NAME = "com.djwebpros.validator.";
	
	public static final String LOGIN_STATUS_LOGGED_IN = "LOGGED_IN";
	public static final String LOGIN_STATUS_LOGGED_OUT = "LOGGED_OUT";
	public static final String LOGIN_STATUS_LOCKED = "LOCKED";
		
	
	//public static final String USER_LEVEL_ = "EXTRA";
	
}
