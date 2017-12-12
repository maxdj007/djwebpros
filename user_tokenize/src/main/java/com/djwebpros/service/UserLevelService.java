package com.djwebpros.service;

import java.util.List;

import com.djwebpros.models.UserLevel;

public interface UserLevelService {

public void addUserLevel(UserLevel UserLevel);
	
	public void updateUserLevel(UserLevel UserLevel);
	
	public UserLevel getUserLevelById(int id);
	
	public void removeUserLevel(int id);
	
	public List<UserLevel> listUserLevels();
	
}
