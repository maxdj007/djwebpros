package com.djwebpros.validator;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * class to validate LogOut Request.
 * @author DJ
 */
public class LogOutRequestValidator extends RequestValidator {

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
    	Properties property = ValidationFactory.getInstance().getPropertiesInstance();
    	String mandatoryValidationFlow = property.getProperty("validationFlow.Mandatory");
		String[] validatorClasses= mandatoryValidationFlow.split(",");
		ValidationFactory.getInstance().performMandatoryParamValidation(errorJson, validatorClasses, postJSONData);
		
		String validationFlow = property.getProperty("validationFlow.LogOut");
		validatorClasses = validationFlow.split(",");
		ValidationFactory.getInstance().performRequestSpecificParamValidation(errorJson, validatorClasses, postJSONData);

	}

}
