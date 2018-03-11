package com.drest.storage.mysql.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.drest.app.model.User;
import com.drest.storage.mysql.service.IUserDao;

public class UserDaoImpl implements IUserDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public void addUser(User user) {
		System.out.println("My Data source is: " + jdbcTemplate);
	}

	@Override
	public void setTemplate(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public User login(String userName, String password) {
		return null;
	}
}
