package com.djwebpros.validator;

import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import com.djwebpros.commons.Constants;

/**
 * 
 * @author DJ
 *
 */
public class ValidatePostFieldpicture extends ValidatePostField {

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
		if(postJSONData.get(Constants.POST_DATA_FIELD_PICTURE) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_PICTURE))){
			if(!validateUrl((String)postJSONData.get(Constants.POST_DATA_FIELD_PICTURE))){
				ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_PICTURE, property.getProperty("Post.Field.Validation.Error.Picture.Invalid.Url"));
			}
			if(validatePicture((String)postJSONData.get(Constants.POST_DATA_FIELD_PICTURE))){
				ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_PICTURE, property.getProperty("Post.Field.Validation.Error.Picture.No.Picture"));
			}
		}

	}
	
	private boolean validateUrl(String url){
		return UrlValidator.getInstance().isValid(url);
	}
	
	private boolean validatePicture(String url){
		return true;
	}


}
