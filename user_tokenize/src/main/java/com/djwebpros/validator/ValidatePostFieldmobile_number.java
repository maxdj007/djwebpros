package com.djwebpros.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.djwebpros.commons.Constants;

/**
 * 
 * @author DJ
 *
 */
public class ValidatePostFieldmobile_number extends ValidatePostField {

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
		if(postJSONData.get(Constants.POST_DATA_FIELD_MOBILE_NUMBER) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_MOBILE_NUMBER))){
			if(!validateMobileNumber((String)postJSONData.get(Constants.POST_DATA_FIELD_MOBILE_NUMBER))){
				ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_MOBILE_NUMBER, property.getProperty("Post.Field.Validation.Error.Mobile.Number.Invalid"));
			}
		}
	}
	
	private boolean validateMobileNumber(String number){
		String regx = property.getProperty("Mobile.Number.Validation.Regex");
		Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(number);
	    return matcher.find();
	}	
}
