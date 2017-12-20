package com.djwebpros.JWT;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.djwebpros.models.User;

/**
 * 
 * @author DJ
 *
 */
@Service
public class JWTokenClaimAccessImpl implements JWTokenClaimAccess {

	public User getUserFromToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getTokenCreationDate(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTimeToLivefromToken(String token) {
		// TODO Auto-generated method stub
		return 0;
	}

}
