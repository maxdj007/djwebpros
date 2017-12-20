package com.djwebpros.JWT;

import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.responses.JWTMethodReturn;

/**
 * 
 * @author DJ
 *
 */
public interface JWTokenCreator {

	public JWTMethodReturn createJWT(User user);
	public JWTMethodReturn createJWT(User user, Token token);
	
}
