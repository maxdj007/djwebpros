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
 * Model class for user levels defined by the Administrator
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "user_levels")
public class UserLevel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@Column(name="level", length = 50)
	private String level;
	
	@Column(name="level_permissions", length = 50)
	private String levelPermissions;
	
	@Column(name="is_admin")
	private boolean isAdmin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelPermissions() {
		return levelPermissions;
	}

	public void setLevelPermissions(String levelPermissions) {
		this.levelPermissions = levelPermissions;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
