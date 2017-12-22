package com.djwebpros.validator;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.djwebpros.commons.PropertiesFileLoader;

/**
 * This class is used to validate all required validations.
 * 
 * @author DJ
 */
public final class ValidationFactory {
	
	/**
	 * Properties file loader
	 */
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();

	/**
	 * Property
	 */
	protected Properties property = propertiesLoader.getMiscProperties();
	/**
	 *
	 */
	private static ValidationFactory validationFactory;

	/**
	 * Private Constructor to prevent any other class from instantiating. load
	 * the properties files.
	 */
	private ValidationFactory() {

	}

	/**
	 * @return :it returns the object of ValidationFactory.
	 */
	public static ValidationFactory getInstance() {

		if (validationFactory == null) {
			validationFactory = new ValidationFactory();
		}

		return validationFactory;

	}

	/**
	 * @param errorJson
	 *            : object that will hold all the errors in a key value pair.
	 * @param ValidatorClass
	 *            : Request type that need to be validated
	 * @param postJSONData
	 *            : All the data submitted in the request to the controller.
	 * @throws ClassNotFoundException
	 */
	public void performRequestValidation(final JSONObject errorJson,
			String validatorClass, JSONObject postJSONData) {
		try {
				validatorClass = validatorClass+"RequestValidator";
				Class<?> valClass = Class.forName(validatorClass);
				RequestValidator validator = (RequestValidator) valClass.newInstance();
				validator.validate(errorJson, postJSONData);

		} catch (NullPointerException e) {
			

		} catch (JSONException e) {
			

		} catch (LinkageError | ClassNotFoundException | IllegalAccessException | InstantiationException
				| SecurityException e) {

		}
	}
	
	/**
	 * @param errorJson
	 *            : object that will hold all the errors in a key value pair.
	 * @param ValidatorClass
	 *            : Request type that need to be validated
	 * @param postJSONData
	 *            : All the data submitted in the request to the controller.
	 * @throws ClassNotFoundException
	 */
	public void performMandatoryParamValidation(final JSONObject errorJson,
			String[] validatorClasses, JSONObject postJSONData) {
		try {
				for(String validatorClass : validatorClasses){
					validatorClass = "ValidatePostField"+validatorClass;
					Class<?> valClass = Class.forName(validatorClass);
					ValidatePostField validator = (ValidatePostField) valClass.newInstance();
					validator.validate(errorJson, postJSONData);
				}

		} catch (NullPointerException e) {
			

		} catch (JSONException e) {
			

		} catch (LinkageError | ClassNotFoundException | IllegalAccessException | InstantiationException
				| SecurityException e) {

		}
	}
	
	/**
	 * @param errorJson
	 *            : object that will hold all the errors in a key value pair.
	 * @param ValidatorClass
	 *            : Request type that need to be validated
	 * @param postJSONData
	 *            : All the data submitted in the request to the controller.
	 * @throws ClassNotFoundException
	 */
	public void performRequestSpecificParamValidation(final JSONObject errorJson,
			String[] validatorClasses, JSONObject postJSONData) {
		try {
				for(String validatorClass: validatorClasses){
					validatorClass = "ValidatePostField"+validatorClass;
					Class<?> valClass = Class.forName(validatorClass);
					ValidatePostField validator = (ValidatePostField) valClass.newInstance();
					validator.validate(errorJson, postJSONData);
				}

		} catch (NullPointerException e) {
			

		} catch (JSONException e) {
			

		} catch (LinkageError | ClassNotFoundException | IllegalAccessException | InstantiationException
				| SecurityException e) {

		}
	}
	
	public Properties getPropertiesInstance(){
		return property;
	}

	/**
	 * @method setErrorMessage : to set error message with a key value.
	 * @param errorJSON
	 *            : stores error in key value pair.
	 * @param fieldName
	 *            : key value.
	 * @param errorMessage
	 *            : a default error.
	 * @return return the object, errorJSON
	 */
	public JSONObject setErrorMessage(final JSONObject errorJSON, final String fieldName, final String errorMessage) {
		errorJSON.put(fieldName,"");
		return errorJSON;
	}
}
