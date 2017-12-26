package com.djwebpros.validator;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.djwebpros.commons.Constants;
import com.djwebpros.commons.PropertiesFileLoader;

/**
 * This class is used to validate all required validations.
 * 
 * @author DJ
 */
public final class ValidationFactory {
	
	private Logger logger = Logger.getLogger(ValidationFactory.class);
	/**
	 * Properties file loader
	 */
	protected PropertiesFileLoader propertiesLoader = PropertiesFileLoader.getInstance();

	/**
	 * Property
	 */
	protected Properties miscProperty = propertiesLoader.getMiscProperties();
	
	/**
	 * Property
	 */
	protected Properties validtionFlowProperty = propertiesLoader.getValidationFlowProperties();
	
	
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
		logger.info("Starting the request validations");
		try {
				logger.info(validatorClass+" request validation initiated");
				validatorClass = Constants.VALIDATION_PACKAGE_ABSOLUTE_NAME+validatorClass+"RequestValidator";
				Class<?> valClass = Class.forName(validatorClass);
				RequestValidator validator = (RequestValidator) valClass.newInstance();
				validator.validate(errorJson, postJSONData);

		} catch (NullPointerException e) {
			logger.error("NullPointerException exception occured : with message :"+e.getMessage());
		} catch (JSONException e) {
			logger.error("JSONException exception occured : with message :"+e.getMessage());
		} catch (LinkageError | ClassNotFoundException | IllegalAccessException | InstantiationException
				| SecurityException e) {
			logger.error("exception occured : with message :"+e.getMessage());
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
		logger.info("Starting the Mandatory Param validations");
		try {
				for(String validatorClass : validatorClasses){
					validatorClass = Constants.VALIDATION_PACKAGE_ABSOLUTE_NAME+"ValidatePostField"+validatorClass;
					Class<?> valClass = Class.forName(validatorClass);
					ValidatePostField validator = (ValidatePostField) valClass.newInstance();
					validator.validate(errorJson, postJSONData);
				}

		} catch (NullPointerException e) {
			logger.error("NullPointerException exception occured : with message :"+e.getMessage());
		} catch (JSONException e) {
			logger.error("JSONException exception occured : with message :"+e.getMessage());
		} catch (LinkageError | ClassNotFoundException | IllegalAccessException | InstantiationException
				| SecurityException e) {
			logger.error("exception occured : with message :"+e.getMessage());
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
		logger.info("Starting the request specific param validations");
		try {
				for(String validatorClass: validatorClasses){
					validatorClass = Constants.VALIDATION_PACKAGE_ABSOLUTE_NAME+"ValidatePostField"+validatorClass;
					Class<?> valClass = Class.forName(validatorClass);
					ValidatePostField validator = (ValidatePostField) valClass.newInstance();
					validator.validate(errorJson, postJSONData);
				}

		} catch (NullPointerException e) {
			logger.error("NullPointerException exception occured : with message :"+e.getMessage());
		} catch (JSONException e) {
			logger.error("JSONException exception occured : with message :"+e.getMessage());
		} catch (LinkageError | ClassNotFoundException | IllegalAccessException | InstantiationException
				| SecurityException e) {
			logger.error("exception occured : with message :"+e.getMessage());
		}
	}
	
	public Properties getPropertiesInstance(){
		return miscProperty;
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
