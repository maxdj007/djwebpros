package com.djwebpros.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author DJ
 * 
 * Token given to every user who access the website
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "tokenize")
public class Token implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@Column(name="user_hash", length = 100)
	private String user_hash;
	
	@Column(name="token", length = 512)
	private String token;
	
	@Column(name="logged_in")
	private int loggedIn;
	
	@Column(name="avail_date")
	private Date availDate;
	
	@Column(name="is_valid")
	private boolean isValid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_hash() {
		return user_hash;
	}

	public void setUser_hash(String user_hash) {
		this.user_hash = user_hash;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(int loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
