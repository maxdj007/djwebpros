package com.djwebpros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djwebpros.DAO.UserDAO;
import com.djwebpros.models.User;
import com.djwebpros.responses.LoginResponseModel;

/**
 * 
 * @author DJ
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	@Override
	@Transactional
	public void addUser(User User) {
		this.userDAO.addUser(User);
	}

	@Override
	@Transactional
	public void updateUser(User User) {
		this.userDAO.updateUser(User);
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return this.userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDAO.listUsers();
	}

	@Override
	@Transactional
	public LoginResponseModel userLoginCheck(User user) {
		return this.userDAO.userLoginCheck(user);
	}

}
