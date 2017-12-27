package com.djwebpros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djwebpros.DAO.UserTypeDAO;
import com.djwebpros.models.UserType;

/**
 * 
 * @author DJ
 *
 */
@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	private UserTypeDAO userTypeDAO;
	
	public void setUserTypeDAO(UserTypeDAO userTypeDAO){
		this.userTypeDAO = userTypeDAO;
	}
	
	@Override
	@Transactional
	public void addUserType(UserType UserType) {
		this.userTypeDAO.addUserType(UserType);
	}

	@Override
	@Transactional
	public void updateUserType(UserType UserType) {
		this.userTypeDAO.updateUserType(UserType);
		
	}

	@Override
	@Transactional
	public UserType getUserTypeById(int id) {
		return this.userTypeDAO.getUserTypeById(id);
	}

	@Override
	@Transactional
	public void removeUserType(int id) {
		this.removeUserType(id);
	}

	@Override
	@Transactional
	public List<UserType> listUserTypes() {
		return this.listUserTypes();
	}

}
