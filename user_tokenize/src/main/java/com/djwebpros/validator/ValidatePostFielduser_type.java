package com.djwebpros.validator;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.djwebpros.commons.Constants;

/**
 * 
 * @author DJ
 *
 */
public class ValidatePostFielduser_type extends ValidatePostField {

	@Override
	/**
	 * @method : validation check.
	 * @param postJSONData
	 *            : JSON Object containing all the parameters sent in the
	 *            request
	 * @param errorJson
	 *            : object that will hold all the errors in a key value pair.
	 */
	public void validate(JSONObject errorJson, JSONObject postJSONData) throws JSONException {
		if(postJSONData.get(Constants.POST_DATA_FIELD_USER_TYPE) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_USER_TYPE))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_USER_LEVEL, property.getProperty("Post.Field.Validation.Error.User.Type.Null"));
		} else if(!validateUserType((String)postJSONData.get(Constants.POST_DATA_FIELD_USER_TYPE))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_USER_TYPE, property.getProperty("Post.Field.Validation.Error.User.Type.Invalid"));
		}

	}
	
	private boolean validateUserType(String userType){
		return true;
	}

}
