package com.djwebpros.DAO;

import java.util.List;

import com.djwebpros.models.UserType;

public interface UserTypeDAO {

	public void addUserType(UserType UserType);
	
	public void updateUserType(UserType UserType);
	
	public UserType getUserTypeById(int id);
	
	public void removeUserType(int id);
	
	public List<UserType> listUserTypes();
	
}
