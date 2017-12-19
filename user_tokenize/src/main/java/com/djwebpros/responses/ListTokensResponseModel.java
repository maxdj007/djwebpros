/**
 * 
 */
package com.djwebpros.responses;

import java.util.List;

import com.djwebpros.models.Token;

/**
 * @author DJ
 *
 */
public class ListTokensResponseModel extends ResponseModel {

	private List<Token> tokens;

	/**
	 * @return the tokens
	 */
	public List<Token> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	
}
