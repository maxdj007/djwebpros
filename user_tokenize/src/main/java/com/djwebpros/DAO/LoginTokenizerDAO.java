package com.djwebpros.DAO;

import java.util.HashMap;
import java.util.List;

import com.djwebpros.commons.MethodCallReturn;
import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.models.UserLevel;
import com.djwebpros.models.UserType;

public interface LoginTokenizerDAO {
	
	/**
	 * Method to fetch user from DB based on user ID.
	 * 
	 * @param id
	 * 			id of the user
	 * @return object of User
	 */
	User getUserFromDB(int id);
	
	/**
	 * Method to fetch user from DB based on user hash.
	 * 
	 * @param userHash
	 * 			id of the user
	 * @return object of User
	 */
	User getUserFromDB(String userHash);
	
	/**
	 * Get a list of users to be fetched on the basis on parameters
	 * 
	 * @param parameters
	 * @return List of users
	 */
	List<User> getUsersFromDB(HashMap<String,String> parameters);
	
	/**
	 * Add user to db
	 * 
	 * @param user
	 * @return Object of MethodCallReturn
	 */
	MethodCallReturn addUserToDB(User user);
	
	/**
	 * Update User in the db
	 * 
	 * @param user
	 * @return Object of MethodCallReturn
	 */
	MethodCallReturn updateUser(User user);
	
	/**
	 * update a list of users in DB
	 * 
	 * @param users
	 * @return Object of MethodCallReturn
	 */
	MethodCallReturn upadateUsers(List<User> users);
	
	/**
	 * Delete the list of the given users.
	 * 
	 * @param users
	 * @return Object of MethodCallReturn
	 */
	MethodCallReturn deleteUsers(List<User> users);
	
	/**
	 * Delete a single user from the DB
	 * 
	 * @param user
	 * @return Object of MethodCallReturn
	 */
	MethodCallReturn deleteUser(User user); 
	
	/**
	 * add token to the DB
	 * 
	 * @param token
	 * @return object of Token
	 */
	Token addToken(Token token);
	
	/**
	 * fetch token for a single user 
	 * latest token assigned to the user will be returned
	 * 
	 * @param user
	 * @return object of Token
	 */
	Token fetchToken(User user);
	
	/**
	 * update a token
	 * 
	 * @param token
	 * @return object of MethodCallReturn
	 */
	MethodCallReturn updateToken(Token token);
	
	/**
	 * Update a list of tokens
	 * 
	 * @param tokens
	 * @return object of MethodCallReturn
	 */
	MethodCallReturn updateTokens(List<Token> tokens);
	
	/**
	 * delete a list of tokens
	 * 
	 * @param tokens
	 * @return object of MethodCallReturn
	 */
	MethodCallReturn deleteTokens(List<Token> tokens);
	
	/**
	 * delete a single token
	 * 
	 * @param token
	 * @return object of MethodCallReturn
	 */
	MethodCallReturn deleteTokens(Token token);
	
	/**
	 * get user level from the db on the basis of UserLevel id
	 * 
	 * @param id
	 * @return UserLevel
	 */
	UserLevel getUserLevelFromDB(int id);
	
	/**
	 * add a user level to the db
	 * 
	 * @param userLevel
	 * 			user level to be added
	 * @param user
	 * 			Authenticated user to add the user level
	 * @return object of MethodCallReturn
	 */
	MethodCallReturn addUserLevel(UserLevel userLevel, User user);
	
	/**
	 * get list of user levels on the basis of given parameters
	 * 
	 * @param parameters
	 * @return object of MethodCallReturn
	 */
	List<UserLevel> getUserLevelsFromDB(HashMap<String,String> parameters);
	
	/**
	 * get user type from DB on the basis of user type id
	 * 
	 * @param id
	 * @return UserType
	 */
	UserType getUserTypeFromDB(int id);
	
	/**
	 * add user type to the DB
	 * 
	 * @param userType
	 * 			UserType to added to the db
	 * @param user
	 * 			Authenticated user to add the user type to DB
	 * @return MethodCallReturn
	 */
	MethodCallReturn addUserType(UserType userType, User user);
	
	/**
	 * get the list of user types from DB on the basis of given parameters
	 * 
	 * @param parameters
	 * @return List<UserType>
	 */
	List<UserType> getUserTypesFromDB(HashMap<String,String> parameters);
	
}
