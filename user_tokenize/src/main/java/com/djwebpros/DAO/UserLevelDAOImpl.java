package com.djwebpros.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.djwebpros.models.UserLevel;

/**
 * 
 * @author DJ
 *
 */
@Repository
public class UserLevelDAOImpl implements UserLevelDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addUserLevel(UserLevel UserLevel) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(UserLevel);
	}

	public void updateUserLevel(UserLevel UserLevel) {
		Session session = sessionFactory.getCurrentSession();
		session.update(UserLevel);
	}

	public UserLevel getUserLevelById(int id) {
		Session session = sessionFactory.getCurrentSession();		
		UserLevel UserLevel = (UserLevel) session.get(UserLevel.class, new Integer(id));
		return UserLevel;
	}

	public void removeUserLevel(int id) {
		Session session = sessionFactory.getCurrentSession();
		UserLevel UserLevel = (UserLevel) session.get(UserLevel.class, new Integer(id));
		if(null != UserLevel){
			session.delete(UserLevel);
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<UserLevel> listUserLevels() {
		Session session = sessionFactory.getCurrentSession();
		List<UserLevel> UserLevelsList = session.createQuery("from UserLevel").list();
		return UserLevelsList;
	}

}
