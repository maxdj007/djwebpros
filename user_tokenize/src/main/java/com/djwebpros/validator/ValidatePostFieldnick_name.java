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
public class ValidatePostFieldnick_name extends ValidatePostField {

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
		if(postJSONData.get(Constants.POST_DATA_FIELD_NICK_NAME) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_NICK_NAME))){
			if(!validateNickName((String)postJSONData.get(Constants.POST_DATA_FIELD_NICK_NAME))){
				ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_NICK_NAME, property.getProperty("Post.Field.Validation.Error.Nick.Name.Invalid"));
			}
		}
	}
	
	private boolean validateNickName(String name){
		String regx = property.getProperty("Nick.Name.Validation.Regex");
	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(name);
	    return matcher.find();
	}

}
