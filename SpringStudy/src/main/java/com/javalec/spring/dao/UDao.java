package com.javalec.spring.dao;

import com.javalec.spring.dto.UDto;

public interface UDao {

	public UDto login(String userID);
	public void join(String userID, String userPassword, String userName, String userGender, String userEamil);
}
