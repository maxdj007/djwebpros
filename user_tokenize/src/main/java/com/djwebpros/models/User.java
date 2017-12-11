package com.djwebpros.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author DJ
 * 
 * Model class for the users who sign up.
 *
 */


@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@Column(name="user_hash", length = 100)
	private String userHash;
	
	@Column(name="first_name", length = 60)
	private String firstName;
	
	@Column(name="last_name", length = 60)
	private String lastName;
	
	@Column(name="middle_name", length = 60)
	private String middleName;
	
	@Column(name="nick_name", nullable = true, length = 60)
	private String nickName;
	
	@Column(name="email_id", length = 100)
	private String emailId;
	
	@Column(name="pic", nullable = true, length = 200)
	private String pic;
	
	@Column(name="pass_hash", length = 100)
	private String passHash;
	
	@Column(name="mobile_number", nullable = true, length = 15)
	private String mobileNumber;
	
	@Column(name="user_level")
	@ManyToOne
	private UserLevel userLevel;
	
	@Column(name="user_type", nullable = true)
	@ManyToOne
	private UserType userType;
	
	@Column(name="date_of_birth", nullable = true)
	private Date dateOfBirth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserHash() {
		return userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

}
