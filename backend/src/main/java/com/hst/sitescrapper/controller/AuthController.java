package com.hst.sitescrapper.controller;

import com.hst.sitescrapper.model.api.ApiResponse;
import com.hst.sitescrapper.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtService jwtService;

	@PostMapping("sign-in")
	public ApiResponse signin(@RequestBody  SigninRequest signinRequest) {
		// TODO DB 에서 User 조회 -> Password 대조 작업
		Map<String, Object> tempUser = new HashMap<>();
		tempUser.put("id", signinRequest.id);
		tempUser.put("password", signinRequest.password);
		tempUser.put("name", "HyunGyu-Lee");

		return ApiResponse.of(HttpStatus.OK, jwtService.createToken("user", tempUser, "authorizedUser"));
	}

	public static class SigninRequest {
		private String id;
		private String password;
	}

}
