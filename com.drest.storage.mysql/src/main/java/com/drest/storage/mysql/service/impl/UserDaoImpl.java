package com.drest.storage.mysql.service.impl;

import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import com.drest.app.exceptions.AppException;
import com.drest.app.exceptions.AppException.ERROR_CODE;
import com.drest.app.exceptions.AuthException;
import com.drest.app.model.SingleColumn;
import com.drest.app.model.User;
import com.drest.app.utils.EncryptionUtil;
import com.drest.app.utils.log.EngineLoggerFactory;
import com.drest.engine.Engine;
import com.drest.storage.mysql.rowmapper.SingleColumnMapper;
import com.drest.storage.mysql.rowmapper.UserRowMapper;
import com.drest.storage.mysql.service.IUserDao;

public class UserDaoImpl implements IUserDao {

	private static final String INSERT_USER_SQL =
			"insert into users(id,name,password,first_name, last_name,email,phone_code,phone,is_system_user,is_active,created_on,last_updated,location_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String GET_USER_SQL =
			"select id, name, password, first_name, last_name, email, phone_code, phone, is_system_user, is_active, created_on, last_updated, location_id from users where name = ?";

	private static final String CHECK_USERNAME_SQL =
			"select count(*) as count from users where name = ?";

	private JdbcTemplate jdbcTemplate;

	public void setTemplate(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public void addUser(User user) throws AppException {
		String encryptedPassword =
				EncryptionUtil.encrypt(user.getPassword(), Engine.getInstance().getEncyptionSalt());

		jdbcTemplate.update(
				INSERT_USER_SQL,
				user.getId(),
				user.getName(),
				encryptedPassword,
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPhoneCode(),
				user.getPhone(),
				user.getIsSystemUser(),
				user.getIsActive(),
				new Date(),
				new Date(),
				-1

		);
		EngineLoggerFactory.info("Created User: " + user.getName());
	}

	@Override
	public User login(String userName, String password) throws AppException {
		User user = jdbcTemplate
				.queryForObject(GET_USER_SQL, new Object[] { userName }, new UserRowMapper());
		if (user == null)
			throw new AuthException(ERROR_CODE.LOGIN_001, "UserName doesnot exist", null);

		String savedPassword = user.getPassword();
		String loginEncyptedPassword =
				EncryptionUtil.encrypt(password, Engine.getInstance().getEncyptionSalt());

		//Set password to empty before sending it to as response.
		user.setPassword("");
		if (!loginEncyptedPassword.equals(savedPassword))
			throw new AuthException(ERROR_CODE.LOGIN_002, "Password doesnot match", null);
		
		return user;
	}

	@Override
	public boolean userNameExists(String userName) {
		SingleColumn col = jdbcTemplate.queryForObject(
				CHECK_USERNAME_SQL,
				new Object[] { userName },
				new SingleColumnMapper());
		return col != null && col.getCol() > 0;
	}
}
