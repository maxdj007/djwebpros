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
public class ValidatePostFieldis_logged_in extends ValidatePostField {

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
		if(postJSONData.get(Constants.POST_DATA_FIELD_IS_LOGGED_IN) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_IS_LOGGED_IN))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_IS_LOGGED_IN, property.getProperty("Post.Field.Validation.Error.Is.Logged.In.Null"));
		} else if(!validateLoggedInField((String)postJSONData.get(Constants.POST_DATA_FIELD_IS_LOGGED_IN))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_IS_LOGGED_IN, property.getProperty("Post.Field.Validation.Error.Is.Logged.In.Invalid"));
		}
	}
	
	private boolean validateLoggedInField(String message){
		return true;
	}
}
