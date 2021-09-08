package com.hst.scrapper.user.ui

import com.hst.scrapper.user.application.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @author dlgusrb0808@gmail.com
 */
@RestController
@RequestMapping("/api/users")
class AuthController(
    val userService: UserService
) {

    @PostMapping("/signin")
    fun signin() {
        TODO("로그인 기능 구현")
    }

}