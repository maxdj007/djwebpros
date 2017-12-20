package com.djwebpros.validator;

import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author DJ
 */
public abstract class RequestValidator {
/**
 * abstract method that is overloaded by the field validation classes.
 * @param errorJson :object that will hold all the errors in a key value pair.
 * @throws JSONException :exception.
 */
   public abstract void validate(
           JSONObject errorJson, JSONObject postJSONData)throws JSONException;

}
