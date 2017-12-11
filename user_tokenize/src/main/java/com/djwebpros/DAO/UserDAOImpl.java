package com.djwebpros.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.djwebpros.models.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addUser(User User) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(User);
	}

	public void updateUser(User User) {
		Session session = sessionFactory.getCurrentSession();
		session.update(User);	
	}

	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();		
		User User = (User) session.get(User.class, new Integer(id));
		return User;
	}

	public void removeUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		User User = (User) session.get(User.class, new Integer(id));
		if(null != User){
			session.delete(User);
		}
		
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<User> listUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> UsersList = session.createQuery("from User").list();
		return UsersList;
	}

}
