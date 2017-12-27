package com.djwebpros.validator;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.djwebpros.commons.Constants;
import com.djwebpros.commons.Utility;

/**
 * 
 * @author DJ
 *
 */
public class ValidatePostFieldverifying_hash extends ValidatePostField {

	@Autowired
	private Utility utility;
	
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
		if(postJSONData.get(Constants.POST_DATA_FIELD_VERIFYING_HASH) == null || StringUtils.isEmpty(postJSONData.get(Constants.POST_DATA_FIELD_VERIFYING_HASH))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_VERIFYING_HASH, property.getProperty("Post.Field.Validation.Error.User.Hash.Null"));
		} else if(!validateUserHash(postJSONData.getString(Constants.POST_DATA_FIELD_VERIFYING_HASH))){
			ValidationFactory.getInstance().setErrorMessage(errorJson, Constants.POST_DATA_FIELD_VERIFYING_HASH, property.getProperty("Post.Field.Validation.Error.User.Hash.Invalid"));
		}

	}
	
	private boolean validateUserHash(String userHash){
		return utility.UserHashVerifier(userHash);
	}

}
