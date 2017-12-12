package com.djwebpros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djwebpros.DAO.TokenDAO;
import com.djwebpros.models.Token;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenDAO tokenDAO;
	
	public void settokenDAO(TokenDAO tokenDAO) {
		this.tokenDAO = tokenDAO;
	}

	@Transactional
	public void addToken(Token token) {
		tokenDAO.addToken(token);
	}

	@Transactional
	public void updateToken(Token token) {
		tokenDAO.updateToken(token);
	}

	@Transactional
	public Token getTokenById(int id) {
		return tokenDAO.getTokenById(id);
	}

	@Transactional
	public void removeToken(int id) {
		tokenDAO.removeToken(id);
	}

	@Transactional
	public List<Token> listTokens() {
		return this.tokenDAO.listTokens();
	}

}
