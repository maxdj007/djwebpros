package com.djwebpros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djwebpros.DAO.UserLevelDAO;
import com.djwebpros.models.UserLevel;

/**
 * 
 * @author DJ
 *
 */
@Service
public class UserLevelServiceImpl implements UserLevelService {

	@Autowired
	private UserLevelDAO userlevelDAO;
	
	public void setUserDAO(UserLevelDAO userlevelDAO) {
		this.userlevelDAO = userlevelDAO;
	}
	
	@Transactional
	public void addUserLevel(UserLevel UserLevel) {
		this.userlevelDAO.addUserLevel(UserLevel);
	}

	@Transactional
	public void updateUserLevel(UserLevel UserLevel) {
		this.userlevelDAO.updateUserLevel(UserLevel);
		
	}

	@Transactional
	public UserLevel getUserLevelById(int id) {
		return this.userlevelDAO.getUserLevelById(id);
	}

	@Transactional
	public void removeUserLevel(int id) {
		this.removeUserLevel(id);
	}

	@Transactional
	public List<UserLevel> listUserLevels() {
		return this.listUserLevels();
	}

}
