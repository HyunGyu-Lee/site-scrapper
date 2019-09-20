package com.hst.sitescrapper.model.response;

import com.hst.sitescrapper.model.entity.UserEntity;

/**
 * @author dlgusrb0808@gmail.com
 */
public class UserResponse {
	private String id;
	private String email;
	private String name;

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public static UserResponse convert(UserEntity entity) {
		UserResponse response = new UserResponse();
		response.id = entity.getLoginId();
		response.email = entity.getEmail();
		response.name = entity.getName();
		return response;
	}
}
