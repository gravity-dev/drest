package com.drest.app.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements IModel {

	@Override
	public String getModelName() {
		return "users";
	}

	private long id;
	private String name;
	private String password;
	private int isSystemUser;
	private String firstName;
	private String lastName;
	private String email;
	private int isActive;
	private Date createOn;
	private Date lastUpdated;

}
