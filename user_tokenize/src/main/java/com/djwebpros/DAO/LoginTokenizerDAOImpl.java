package com.djwebpros.DAO;

import java.util.HashMap;
import java.util.List;

import com.djwebpros.commons.MethodCallReturn;
import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.models.UserLevel;
import com.djwebpros.models.UserType;

public class LoginTokenizerDAOImpl implements LoginTokenizerDAO {

	public User getUserFromDB(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserFromDB(String userHash) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersFromDB(HashMap<String, String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn addUserToDB(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn upadateUsers(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn deleteUsers(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Token addToken(Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	public Token fetchToken(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn updateToken(Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn updateTokens(List<Token> tokens) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn deleteTokens(List<Token> tokens) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn deleteTokens(Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserLevel getUserLevelFromDB(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn addUserLevel(UserLevel userLevel, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserLevel> getUserLevelsFromDB(HashMap<String, String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserType getUserTypeFromDB(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn addUserType(UserType userType, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserType> getUserTypesFromDB(HashMap<String, String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
