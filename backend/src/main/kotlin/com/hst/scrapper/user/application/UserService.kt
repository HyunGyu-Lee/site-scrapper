package com.hst.scrapper.user.application

import com.hst.scrapper.user.domain.repo.AuthTokenRepository
import com.hst.scrapper.user.domain.exception.AuthorizationFailException
import com.hst.scrapper.user.ui.request.SigninRequest
import com.hst.scrapper.user.ui.response.SigninResponse
import com.hst.scrapper.global.exception.NotFoundException
import com.hst.scrapper.user.domain.entity.User
import com.hst.scrapper.user.domain.repo.UserRepository
import com.hst.scrapper.user.ui.response.UserResponse
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authTokenRepository: AuthTokenRepository
) : UserDetailsService {

    override fun loadUserByUsername(loginId: String): UserDetails {
        return userRepository.findByLoginId(loginId) ?: throw UsernameNotFoundException("Can not find user - $loginId")
    }

    fun getUserByLoginId(loginId: String): User {
        return userRepository.findByLoginId(loginId) ?: throw NotFoundException("Not Found user. loginId: ${loginId}")
    }

    fun signin(request: SigninRequest): SigninResponse {
        val user = getUserByLoginId(request.loginId)
        if (!passwordEncoder.matches(request.loginPassword, user.loginPassword)) {
            throw AuthorizationFailException()
        }
        return SigninResponse("", UserResponse.fromEntity(user))
    }
}