package com.djwebpros.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.djwebpros.commons.Constants;
import com.djwebpros.models.User;
import com.djwebpros.responses.LoginResponseModel;

/**
 * 
 * @author DJ
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {
	
	private Logger logger = Logger.getLogger(UserDAOImpl.class);

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
	
	public LoginResponseModel userLoginCheck(User givenUser){
		LoginResponseModel loginResponse = new LoginResponseModel();
		EntityManager entityManager = sessionFactory.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery( User.class );
		Root<User> root = query.from( User.class );
		query.select(root).where(
		    builder.and(
		        builder.equal(root.get("emailId"), givenUser.getEmailId()),
		        builder.equal(root.get("passHash"), givenUser.getPassHash())
		    )
		);
		User user = entityManager.createQuery( query ).getSingleResult();
		if(givenUser.getEmailId().equals(user.getEmailId())){
			if(!user.isLoggedIn()){
				user.setLoggedIn(true);
				this.updateUser(user);
			}
			loginResponse.setError(false);
			loginResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			loginResponse.setUser(user);
			logger.info("User is : " + user.getEmailId() + user.getFirstName() + user.getPassHash());
			return loginResponse;
		}
		loginResponse.setError(false);
		loginResponse.setStatus(Constants.METHOD_CALL_RETURN_STATUS_LOGIN_FAILURE_NO_USER);
		loginResponse.setUser(user);
		return loginResponse;
	}

}
