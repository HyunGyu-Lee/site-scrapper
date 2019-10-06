package com.hst.sitescrapper.constants;

/**
 * @author dlgusrb0808@gmail.com
 */
public class GlobalConstants {
	/***
	 * JWT 인증 관련 상수
	 */
	public static final class JWT {
		public static final String AUTHORIZATION_HEADER = "Authorization";
		public static final String Bearer = "Bearer";
	}

	/***
	 * 권한 관련 상수
	 */
	public static final class Roles {
		private static final String ROLE_PREFIX = "ROLE_";
		public static final String NORMAL_USER = "USER";
		public static final String ADMIN = "ADMIN";
		public static final String NORMAL_USER_ROLE = role(NORMAL_USER);
		public static final String ADMIN_ROLE = role(ADMIN);
		public static String role(String role) {
			return ROLE_PREFIX + role;
		}
	}

}
