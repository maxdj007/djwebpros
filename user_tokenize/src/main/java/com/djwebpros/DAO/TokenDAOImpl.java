package com.djwebpros.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.djwebpros.models.Token;


@Repository
public class TokenDAOImpl implements TokenDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addToken(Token Token) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(Token);
		
	}

	public void updateToken(Token Token) {
		Session session = sessionFactory.getCurrentSession();
		session.update(Token);
		
	}

	public Token getTokenById(int id) {
		Session session = sessionFactory.getCurrentSession();		
		Token Token = (Token) session.get(Token.class, new Integer(id));
		return Token;
	}

	public void removeToken(int id) {
		Session session = sessionFactory.getCurrentSession();
		Token Token = (Token) session.get(Token.class, new Integer(id));
		if(null != Token){
			session.delete(Token);
		}
		
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Token> listTokens() {
		Session session = sessionFactory.getCurrentSession();
		List<Token> TokensList = session.createQuery("from Token").list();
		return TokensList;
	}

}
