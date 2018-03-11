package com.drest.storage.mysql.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.drest.engine.service.IStorageService;
import com.drest.storage.mysql.service.IUserDao;

public class MysqlStorageServiceFacade implements IStorageService {

	public MysqlStorageServiceFacade() {
		super();
	}

	private MysqlStorageServiceFacade storageServiceFacade = null;
	private UserDaoImpl userDao = new UserDaoImpl();

	public void setTemplate(JdbcTemplate template) {
		userDao.setTemplate(template);
	}

	@Override
	public IStorageService instance() {
		if (storageServiceFacade == null) {
			storageServiceFacade = new MysqlStorageServiceFacade();
		}
		return storageServiceFacade;
	}

	public IUserDao getUserDao() {
		return this.userDao;
	}
}
