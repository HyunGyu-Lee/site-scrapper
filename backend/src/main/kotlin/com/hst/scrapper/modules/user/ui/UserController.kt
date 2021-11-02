package com.hst.scrapper.modules.user.ui

import com.hst.scrapper.global.model.api.ApiResponse
import com.hst.scrapper.modules.user.application.UserService
import com.hst.scrapper.modules.user.ui.request.SigninRequest
import com.hst.scrapper.modules.user.ui.request.SignupRequest
import com.hst.scrapper.modules.user.ui.response.SigninResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/users")
class UserController(
    val userService: UserService
) {

    @PostMapping("/signin")
    fun signin(@RequestBody request: SigninRequest): ApiResponse<SigninResponse> {
        return ApiResponse.success(userService.signin(request))
    }

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ApiResponse<Any?> {
        userService.signup(request)
        return ApiResponse.success()
    }

}