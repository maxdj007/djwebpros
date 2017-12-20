package com.djwebpros.DAO;

import java.util.List;

import com.djwebpros.models.Token;

/**
 * 
 * @author DJ
 *
 */
public interface TokenDAO {

	public void addToken(Token Token);
	
	public void updateToken(Token Token);
	
	public Token getTokenById(int id);
	
	public void removeToken(int id);
	
	public List<Token> listTokens();
	
}
