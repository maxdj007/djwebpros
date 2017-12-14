package com.djwebpros.JWT;

import java.util.Date;

import com.djwebpros.models.Token;
import com.djwebpros.models.User;

public interface JWTokenCreator {

	public String createJWT(User user);
	public String createJWT(User user, Token token);
	public boolean verifyJWT(String token);
	public User getUserFromToken(String token);
	public boolean isTokenStillValid(String token);
	public Date getTokenCreationDate(String token);
	public int getTimeToLivefromToken(String token);
	
}
