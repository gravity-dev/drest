package com.drest.storage.mysql.service;

import com.drest.app.model.User;

public interface IUserDao extends IBaseDao {

	public void addUser(User user);
	
	public User login(String userName, String password);

}
