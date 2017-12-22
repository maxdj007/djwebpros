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
	
	@Override
	@Transactional
	public void addUserLevel(UserLevel UserLevel) {
		this.userlevelDAO.addUserLevel(UserLevel);
	}

	@Override
	@Transactional
	public void updateUserLevel(UserLevel UserLevel) {
		this.userlevelDAO.updateUserLevel(UserLevel);
		
	}

	@Override
	@Transactional
	public UserLevel getUserLevelById(int id) {
		return this.userlevelDAO.getUserLevelById(id);
	}

	@Override
	@Transactional
	public void removeUserLevel(int id) {
		this.removeUserLevel(id);
	}

	@Override
	@Transactional
	public List<UserLevel> listUserLevels() {
		return this.listUserLevels();
	}

}
