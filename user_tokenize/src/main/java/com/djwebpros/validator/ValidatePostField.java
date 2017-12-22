package com.djwebpros.validator;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.djwebpros.commons.PropertiesFileLoader;

/**
 *
 * @author DJ
 */
public abstract class ValidatePostField {
	
	/**
	 * Properties file loader
	 */
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();

	/**
	 * Property
	 */
	protected Properties property = propertiesLoader.getMiscProperties();
	/**
	 * abstract method that is overloaded by the field validation classes.
	 * 
	 * @param errorJson
	 *            :object that will hold all the errors in a key value pair.
	 * @throws JSONException
	 *             :exception.
	 */
	public abstract void validate(JSONObject errorJson, JSONObject postJSONData) throws JSONException;
	
	

}