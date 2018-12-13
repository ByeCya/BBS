package com.javalec.spring.dto;

import java.io.Serializable;

public class UDto implements Serializable {

	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;
	
	public UDto() {
		// TODO Auto-generated constructor stub
	}
	
	public UDto(String userID, String userPassword, String userName, String userGender, String userEmail) {
		this.userID = userID;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userGender = userGender;
		this.userEmail = userEmail;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "UDto [userID=" + userID + ", userPassword=" + userPassword + ", userName=" + userName + ", userGender="
				+ userGender + ", userEmail=" + userEmail + "]";
	}
	
	
}
