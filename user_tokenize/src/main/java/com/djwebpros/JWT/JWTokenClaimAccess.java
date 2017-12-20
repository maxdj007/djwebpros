package com.djwebpros.JWT;

import java.util.Date;

import com.djwebpros.models.User;

/**
 * 
 * @author DJ
 *
 */
public interface JWTokenClaimAccess {

	public User getUserFromToken(String token);
	public Date getTokenCreationDate(String token);
	public int getTimeToLivefromToken(String token);
	
}
