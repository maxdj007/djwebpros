package com.djwebpros.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.djwebpros.commons.Constants;

/**
 * 
 * @author DJ
 *
 */
public class ValidatePostFielddob extends ValidatePostField {

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
	 
		if(postJSONData.get(Constants.POST_DATA_FIELD_DATE_OF_BIRTH) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_DATE_OF_BIRTH))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_DATE_OF_BIRTH, property.getProperty("Post.Field.Validation.Error.dob.Null"));
		} else if(!validateDateOfBirth((String)postJSONData.get(Constants.POST_DATA_FIELD_DATE_OF_BIRTH))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_DATE_OF_BIRTH, property.getProperty("Post.Field.Validation.Error.dob.Invalid"));
		}
	}
	
	@SuppressWarnings({"deprecation" })
	private boolean validateDateOfBirth(String dateOfBirth) {
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
		} catch (ParseException e) {
			return false;
		}
		if(date.getTime() > new Date().getTime() || date.getYear() < 0){
			return false;
		}
		return true;
	}

}
