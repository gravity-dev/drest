package com.drest.app.app.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.drest.app.model.User;
import com.drest.engine.Engine;
import com.drest.storage.mysql.service.IUserDao;
import com.drest.storage.mysql.service.impl.MysqlStorageServiceFacade;

@org.springframework.web.bind.annotation.RestController
public class UserController {

	private IUserDao getUserDao() throws Exception {
		return ((MysqlStorageServiceFacade)Engine.getInstance().getStorageService()).getUserDao();
	}

	@RequestMapping("/getuser")
	public String getUser() throws Exception {
		getUserDao().addUser(new User());
		return "{}";
	}
}
