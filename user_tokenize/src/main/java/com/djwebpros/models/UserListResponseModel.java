/**
 * 
 */
package com.djwebpros.models;

import java.util.List;

/**
 * @author DJ
 *
 */
public class UserListResponseModel extends ResponseModel {

	private List<String> users;

	/**
	 * @return the users
	 */
	public List<String> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	
	
}
