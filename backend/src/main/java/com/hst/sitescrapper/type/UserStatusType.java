package com.hst.sitescrapper.type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
public enum UserStatusType {
	EMAIL_NOT_VERIFIED(1, "이메일 미 인증 상태"),
	INACTIVE		  (2, "비활성 상태"),
	ACTIVE			  (3, "활성 상태");

	private int statusCode;
	private String comment;

	private static Map<Integer, UserStatusType> find = new HashMap<>();

	static {
		Arrays.stream(UserStatusType.values()).forEach(type -> find.put(type.statusCode, type));
	}

	UserStatusType(int statusCode, String comment) {
		this.statusCode = statusCode;
		this.comment = comment;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getComment() {
		return comment;
	}

	public static UserStatusType get(int statusCode) {
		return find.get(statusCode);
	}
}
