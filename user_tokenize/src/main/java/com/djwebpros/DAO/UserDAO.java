package com.djwebpros.DAO;

import java.util.List;

import com.djwebpros.models.User;
import com.djwebpros.responses.LoginResponseModel;

/**
 * 
 * @author DJ
 *
 */
public interface UserDAO {
	
	public void addUser(User User);
	
	public void updateUser(User User);
	
	public User getUserById(int id);
	
	public void removeUser(int id);
	
	public List<User> listUsers();
	
	public LoginResponseModel userLoginCheck(User user);
	
	public User getUserByUserHash(User user);
	
	public User getUserByEmailId(User user);

}
