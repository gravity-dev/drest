package com.drest.storage.mysql.service;

import com.drest.app.exceptions.AppException;
import com.drest.app.model.User;

public interface IUserDao extends IBaseDao {

	public void addUser(User user) throws AppException;
	
	public boolean userNameExists(String userName) throws AppException;
	
	public User login(String userName, String password) throws AppException;

}
