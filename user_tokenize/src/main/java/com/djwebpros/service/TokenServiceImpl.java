package com.djwebpros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djwebpros.DAO.TokenDAO;
import com.djwebpros.models.Token;

/**
 * 
 * @author DJ
 *
 */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenDAO tokenDAO;
	
	public void settokenDAO(TokenDAO tokenDAO) {
		this.tokenDAO = tokenDAO;
	}

	@Override
	@Transactional
	public void addToken(Token token) {
		tokenDAO.addToken(token);
	}

	@Override
	@Transactional
	public void updateToken(Token token) {
		tokenDAO.updateToken(token);
	}

	@Override
	@Transactional
	public Token getTokenById(int id) {
		return tokenDAO.getTokenById(id);
	}

	@Override
	@Transactional
	public void removeToken(int id) {
		tokenDAO.removeToken(id);
	}

	@Override
	@Transactional
	public List<Token> listTokens() {
		return this.tokenDAO.listTokens();
	}

}
