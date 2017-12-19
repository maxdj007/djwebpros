package com.djwebpros.JWT;

import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.responses.JWLTMethodReturn;

public interface JWTokenCreator {

	public JWLTMethodReturn createJWT(User user);
	public JWLTMethodReturn createJWT(User user, Token token);
	
}
