package com.djwebpros.validator;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.djwebpros.commons.PropertiesFileLoader;


/**
 *
 * @author DJ
 */
public abstract class RequestValidator {
	
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();
	
	/**
	 * Property
	 */
	protected Properties validtionFlowProperty = propertiesLoader.getValidationFlowProperties();
	
/**
 * abstract method that is overloaded by the field validation classes.
 * @param errorJson :object that will hold all the errors in a key value pair.
 * @throws JSONException :exception.
 */
   public abstract void validate(
           JSONObject errorJson, JSONObject postJSONData)throws JSONException;

}
