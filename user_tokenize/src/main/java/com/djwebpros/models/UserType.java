package com.djwebpros.models;

import java.io.Serializable;

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
 * Model class for user types defined by the Administrator
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "user_type")
public class UserType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@Column(name="user_type", length=50)
	private String userType;
	
	@Column(name="user_misc", length=200)
	private String userMisc;
	
	@Column(name="user_misc2", length=200)
	private String userMisc2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserMisc() {
		return userMisc;
	}

	public void setUserMisc(String userMisc) {
		this.userMisc = userMisc;
	}

	public String getUserMisc2() {
		return userMisc2;
	}

	public void setUserMisc2(String userMisc2) {
		this.userMisc2 = userMisc2;
	}
	
}
