/**
 * 
 */
package com.djwebpros.responses;

import com.djwebpros.models.User;

/**
 * @author DJ
 *
 */
public class UserResponseModel extends ResponseModel {
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
