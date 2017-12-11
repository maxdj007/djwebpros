package com.djwebpros.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.djwebpros.models.UserType;

@Repository
public class UserTypeDAOImpl implements UserTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addUserType(UserType UserType) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(UserType);
		
	}

	public void updateUserType(UserType UserType) {
		Session session = sessionFactory.getCurrentSession();
		session.update(UserType);
		
	}

	public UserType getUserTypeById(int id) {
		Session session = sessionFactory.getCurrentSession();		
		UserType UserType = (UserType) session.get(UserType.class, new Integer(id));
		return UserType;
	}

	public void removeUserType(int id) {
		Session session = sessionFactory.getCurrentSession();
		UserType UserType = (UserType) session.get(UserType.class, new Integer(id));
		if(null != UserType){
			session.delete(UserType);
		}
		
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<UserType> listUserTypes() {
		Session session = sessionFactory.getCurrentSession();
		List<UserType> UserTypesList = session.createQuery("from UserType").list();
		return UserTypesList;
	}

}
