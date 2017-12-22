package com.djwebpros.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.djwebpros.commons.Constants;

/**
 * 
 * @author DJ
 *
 */
public class ValidatePostFieldemail_id extends ValidatePostField {

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
		if(postJSONData.get(Constants.POST_DATA_FIELD_EMAIL_ID) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_EMAIL_ID))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_EMAIL_ID, property.getProperty("Post.Field.Validation.Error.Email.Null"));
		} else if(!validateEmailId((String)postJSONData.get(Constants.POST_DATA_FIELD_EMAIL_ID))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_EMAIL_ID, property.getProperty("Post.Field.Validation.Error.Email.Invalid"));
		}
	}
	
	private boolean validateEmailId(String emailId) {
		return EmailValidator.getInstance().isValid(emailId);
	}

}
