package com.hst.sitescrapper.model.response;

/**
 * @author dlgusrb0808@gmail.com
 */
public class SigninResponse {
	private String authorizedToken;
	private UserResponse user;

	public String getAuthorizedToken() {
		return authorizedToken;
	}

	public UserResponse getUser() {
		return user;
	}

	public static SigninResponse of(String authorizedToken, UserResponse user) {
		SigninResponse signinResponse = new SigninResponse();
		signinResponse.authorizedToken = authorizedToken;
		signinResponse.user = user;
		return signinResponse;
	}
}
