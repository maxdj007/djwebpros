package com.djwebpros.DAO;

import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.djwebpros.commons.Constants;
import com.djwebpros.commons.MethodCallReturn;
import com.djwebpros.models.Token;
import com.djwebpros.models.User;
import com.djwebpros.models.UserLevel;
import com.djwebpros.models.UserType;

public class LoginTokenizerDAOImpl implements LoginTokenizerDAO {

	private static SessionFactory factory;

	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public User getUserFromDB(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			tx.commit();
		} catch (HibernateException e) {
			// LOG this
			if (tx != null)
				tx.rollback();
		} catch (RuntimeException runtimeException) {
			// LOG this
		} finally {
			session.close();
		}
		return user;
	}

	public User getUserFromDB(String userHash) {

		Session session = factory.openSession();
		Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("userHash"), userHash));
			Query<User> q = session.createQuery(query);
			user = q.getSingleResult();
			// log here
			tx.commit();
		} catch (HibernateException e) {
			// LOG this
			if (tx != null)
				tx.rollback();
		} catch (RuntimeException runtimeException) {
			// LOG this
		} finally {
			session.close();
		}
		return user;
	}

	public List<User> getUsersFromDB(HashMap<String, String> parameters) {

		Session session = factory.openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			for (String parameter : parameters.keySet()) {
				tx = session.beginTransaction();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<User> query = builder.createQuery(User.class);
				Root<User> root = query.from(User.class);
				query.select(root).where(builder.equal(root.get(parameter), parameters.get(parameter)));
				Query<User> q = session.createQuery(query);
				users = q.getResultList();
				// log here
				tx.commit();
				break;
			}
		} catch (HibernateException e) {
			// LOG this
			if (tx != null)
				tx.rollback();
		} catch (RuntimeException runtimeException) {
			// LOG this
		} finally {
			session.close();
		}

		return users;

	}

	public MethodCallReturn addUserToDB(User user) {
		MethodCallReturn returnObject = new MethodCallReturn();

		Session session = factory.openSession();
		Transaction tx = null;
		Integer coolId = null;
		try {
			tx = session.beginTransaction();
			coolId = (Integer) session.save(user);
			// log coolid
			// log user.getId();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(e.getMessage());
			// log here
		} catch (RuntimeException runtimeException) {
			// LOG this
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(runtimeException.getMessage());
		} finally {
			// Log here
			session.close();
		}

		if (coolId != null) {
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE);
			returnObject.setMessage("User object Committed successfully");
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUser(user);
		} else {
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR);
			returnObject.setUser(user);
		}

		return returnObject;
	}

	public MethodCallReturn updateUser(User user) {
		MethodCallReturn returnObject = new MethodCallReturn();
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User dbUser = (User) session.get(User.class, user.getId());
			dbUser = user;
			session.update(dbUser);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(e.getMessage());
			// log here
		} catch (RuntimeException runtimeException) {
			// LOG this
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(runtimeException.getMessage());
		} finally {
			session.close();
		}

		if (Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE.equals(returnObject.getError())) {
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE);
			returnObject.setMessage("User object Committed successfully");
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUser(user);
		} else {
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUser(user);
		}

		return returnObject;
	}

	public MethodCallReturn upadateUsers(List<User> users) {
		MethodCallReturn returnObject = new MethodCallReturn();
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			for (User user : users) {
				User dbUser = (User) session.get(User.class, user.getId());
				dbUser = user;
				session.update(dbUser);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(e.getMessage());
			// log here
		} catch (RuntimeException runtimeException) {
			// LOG this
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(runtimeException.getMessage());
		} finally {
			session.close();
		}

		if (Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE.equals(returnObject.getError())) {
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE);
			returnObject.setMessage("User object Committed successfully");
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUsers(users);
		} else {
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUsers(users);
		}

		return returnObject;
	}

	public MethodCallReturn deleteUsers(List<User> users) {
		MethodCallReturn returnObject = new MethodCallReturn();
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			for (User user : users) {
				User employee = (User) session.get(User.class, user.getId());
				session.delete(employee);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(e.getMessage());
			// log here
		} catch (RuntimeException runtimeException) {
			// LOG this
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(runtimeException.getMessage());
		} finally {
			session.close();
		}

		if (Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE.equals(returnObject.getError())) {
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE);
			returnObject.setMessage("User object Committed successfully");
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUsers(users);
		} else {
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUsers(users);
		}

		return returnObject;
	}

	public MethodCallReturn deleteUser(User user) {
		MethodCallReturn returnObject = new MethodCallReturn();
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			User employee = (User) session.get(User.class, user.getId());
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(e.getMessage());
			// log here
		} catch (RuntimeException runtimeException) {
			// LOG this
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(runtimeException.getMessage());
		} finally {
			session.close();
		}

		if (Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE.equals(returnObject.getError())) {
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE);
			returnObject.setMessage("User object Committed successfully");
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUser(user);
		} else {
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setUser(user);
		}

		return returnObject;
	}

	public MethodCallReturn addToken(Token token) {
		MethodCallReturn returnObject = new MethodCallReturn();

		Session session = factory.openSession();
		Transaction tx = null;
		Integer coolId = null;
		
		try {
			tx = session.beginTransaction();
			coolId = (Integer) session.save(token);
			// log coolid
			// log user.getId();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(e.getMessage());
			// log here
		} catch (RuntimeException runtimeException) {
			// LOG this
			if (tx != null)
				tx.rollback();
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_TRUE);
			returnObject.setMessage(runtimeException.getMessage());
		} finally {
			// Log here
			session.close();
		}

		if (coolId != null) {
			returnObject.setError(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR_VALUE_FALSE);
			returnObject.setMessage("User object Committed successfully");
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_SUCCESS);
			returnObject.setToken(token);
		} else {
			returnObject.setStatus(Constants.METHOD_CALL_RETURN_STATUS_VALUE_ERROR);
			returnObject.setToken(token);
		}

		return returnObject;
		
	}

	public Token fetchToken(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn updateToken(Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn updateTokens(List<Token> tokens) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn deleteTokens(List<Token> tokens) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn deleteTokens(Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserLevel getUserLevelFromDB(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn addUserLevel(UserLevel userLevel, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserLevel> getUserLevelsFromDB(HashMap<String, String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserType getUserTypeFromDB(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodCallReturn addUserType(UserType userType, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserType> getUserTypesFromDB(HashMap<String, String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

}
