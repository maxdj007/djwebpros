package com.djwebpros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djwebpros.DAO.UserTypeDAO;
import com.djwebpros.models.UserType;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	private UserTypeDAO userTypeDAO;
	
	public void setUserTypeDAO(UserTypeDAO userTypeDAO){
		this.userTypeDAO = userTypeDAO;
	}
	
	@Transactional
	public void addUserType(UserType UserType) {
		this.userTypeDAO.addUserType(UserType);
	}

	public void updateUserType(UserType UserType) {
		this.userTypeDAO.updateUserType(UserType);
		
	}

	public UserType getUserTypeById(int id) {
		return this.getUserTypeById(id);
	}

	public void removeUserType(int id) {
		this.removeUserType(id);
	}

	public List<UserType> listUserTypes() {
		return this.listUserTypes();
	}

}
