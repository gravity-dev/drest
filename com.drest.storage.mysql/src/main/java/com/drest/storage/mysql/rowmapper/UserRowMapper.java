package com.drest.storage.mysql.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drest.app.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setPhoneCode(rs.getString("phone_code"));
		user.setCreateOn(rs.getDate("created_on"));
		user.setLastUpdated(rs.getDate("last_updated"));
		user.setLocationId(rs.getInt("location_id"));
		user.setIsActive(rs.getInt("is_active"));
		user.setIsSystemUser(rs.getInt("is_system_user"));
		user.setPassword(rs.getString("password"));
		return user;
	}

}
