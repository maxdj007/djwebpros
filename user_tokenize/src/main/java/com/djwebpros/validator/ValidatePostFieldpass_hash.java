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
public class ValidatePostFieldpass_hash extends ValidatePostField {

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
		if(postJSONData.get(Constants.POST_DATA_FIELD_PASS_HASH) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_PASS_HASH))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_PASS_HASH, property.getProperty("Post.Field.Validation.Error.Pass.Hash.Null"));
		} 
	}

}
