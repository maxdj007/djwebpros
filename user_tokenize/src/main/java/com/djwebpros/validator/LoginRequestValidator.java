package com.djwebpros.validator;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * class to validate Login Request.
 * @author DJ
 */
public class LoginRequestValidator extends RequestValidator {
  
    @Override
    /**
    * @method : validation check.
    * @param postJSONData :
    * JSON Object containing all the parameters sent in the request
    * @param errorJson :
    * object that will hold all the errors in a key value pair.
    */
    public void validate(final JSONObject errorJson, JSONObject postJSONData) throws JSONException {
    	String[] validatorClasses = null;
    	Properties property = ValidationFactory.getInstance().getPropertiesInstance();
    	String mandatoryValidationFlow = property.getProperty("validationFlow.Mandatory");
    	if(mandatoryValidationFlow != null){
			validatorClasses= mandatoryValidationFlow.split(",");
			if(validatorClasses.length > 0)
			ValidationFactory.getInstance().performMandatoryParamValidation(errorJson, validatorClasses, postJSONData);
    	}
		
		String validationFlow = property.getProperty("validationFlow.Login");
		if(validationFlow != null){
			validatorClasses = validationFlow.split(",");
			if(validatorClasses.length > 0)
			ValidationFactory.getInstance().performRequestSpecificParamValidation(errorJson, validatorClasses, postJSONData);
		}
    }
}

