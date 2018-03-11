package com.drest.storage.mysql.service;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IBaseDao {

	public void setTemplate(JdbcTemplate template);

}
