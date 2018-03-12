package com.drest.app.app.web.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.drest.app.exceptions.AppException;
import com.drest.app.model.User;
import com.drest.app.utils.AeJsonResponseUtils;
import com.drest.app.utils.AeJsonResponseUtils.STATUS;
import com.drest.app.utils.log.EngineLoggerFactory;
import com.drest.engine.Engine;
import com.drest.storage.mysql.service.IUserDao;
import com.drest.storage.mysql.service.impl.MysqlStorageServiceFacade;
import com.fasterxml.jackson.databind.ObjectMapper;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/user")
public class UserController {

	private IUserDao getUserDao() throws AppException {
		return ((MysqlStorageServiceFacade) Engine.getInstance().getStorageService()).getUserDao();
	}

	@RequestMapping("/exists")
	public String getUser(
			@RequestParam(
					value = "name") String name)
			throws Exception {
		try {
			boolean exists = getUserDao().userNameExists(name);
			return AeJsonResponseUtils.keyResponse("UserExists", String.valueOf(exists));
		} catch (AppException e) {
			EngineLoggerFactory.severe("Failed query for user name exists");
			return AeJsonResponseUtils
					.errorResponse("USER_001", STATUS.Failure, "Failed query for user name exists");
		}
	}

	@RequestMapping(
			value = "/add",
			method = RequestMethod.POST)
	public String addUser(@RequestBody User user) throws Exception {
		try {
			getUserDao().addUser(user);
			return AeJsonResponseUtils.keyResponse(STATUS.Success);
		} catch (AppException e) {
			EngineLoggerFactory.severe("Failed to create user");
			return AeJsonResponseUtils
					.errorResponse("USER_002", STATUS.Failure, "Failed to create user");
		}
	}

	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Map<String, String> payload) throws Exception {
		try {
			String username = payload.get("username");
			String password = payload.get("password");
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				return ResponseEntity
						.badRequest()
						.header(HttpHeaders.CONTENT_TYPE, "application/json")
						.body(
								AeJsonResponseUtils.errorResponse(
										"USER_003",
										STATUS.Failure,
										"username or password cannot be empty."));
			}
			User user = getUserDao().login(payload.get("username"), payload.get("password"));
			ObjectMapper mapper = new ObjectMapper();
			return ResponseEntity
					.accepted()
					.header(HttpHeaders.CONTENT_TYPE, "application/json")
					.body(mapper.writeValueAsString(user));
		} catch (Exception | AppException e) {
			EngineLoggerFactory.severe("User login failed");
			return ResponseEntity
					.accepted()
					.header(HttpHeaders.CONTENT_TYPE, "application/json")
					.body(
							AeJsonResponseUtils.errorResponse(
									"USER_003",
									STATUS.Failure,
									"User login failed: " + e.getLocalizedMessage()));
		}
	}
}
