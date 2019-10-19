package com.hst.sitescrapper.controller;

import com.hst.sitescrapper.model.api.ApiResponse;
import com.hst.sitescrapper.model.request.SigninRequest;
import com.hst.sitescrapper.model.request.SignupRequest;
import com.hst.sitescrapper.model.response.SigninResponse;
import com.hst.sitescrapper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ApiResponse<SigninResponse> signin(@RequestBody SigninRequest request) {
		return new ApiResponse<>(userService.signin(request));
	}

	@PostMapping("/signup")
	public ApiResponse signup(@RequestBody SignupRequest request) {
		userService.signup(request);
		return new ApiResponse();
	}

}
