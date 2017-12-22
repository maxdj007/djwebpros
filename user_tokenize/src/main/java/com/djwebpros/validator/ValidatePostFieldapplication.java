package com.djwebpros.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.djwebpros.commons.Constants;

/**
 * 
 * @author DJ
 *
 */
public class ValidatePostFieldapplication extends ValidatePostField {

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
		String applications = property.getProperty("djwebpros.list.application");
		List<String> applicationsList = new ArrayList<String>(Arrays.asList(applications.split(","))); 
		if(postJSONData.get(Constants.POST_DATA_FIELD_APPLICATION) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_APPLICATION))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_APPLICATION, property.getProperty("Post.Field.Validation.Error.Application.Null"));
		} else if(applicationsList.contains(postJSONData.get(Constants.POST_DATA_FIELD_APPLICATION))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_APPLICATION, property.getProperty("Post.Field.Validation.Error.Application.Invalid"));
		}

	}

}
