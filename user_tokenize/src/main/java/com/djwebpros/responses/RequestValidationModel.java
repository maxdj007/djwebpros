package com.djwebpros.responses;

/**
 * 
 * @author DJ
 *
 */
public class RequestValidationModel extends ResponseModel {
	
	private boolean isRequestValid;
	private String errorsInRequest;
	
	public boolean isRequestValid() {
		return isRequestValid;
	}
	public void setRequestValid(boolean isRequestValid) {
		this.isRequestValid = isRequestValid;
	}
	public String getErrorsInRequest() {
		return errorsInRequest;
	}
	public void setErrorsInRequest(String errorsInRequest) {
		this.errorsInRequest = errorsInRequest;
	}

}
