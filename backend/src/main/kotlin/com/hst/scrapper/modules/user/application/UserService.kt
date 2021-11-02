package com.hst.scrapper.modules.user.application

import com.hst.scrapper.modules.user.domain.entity.User
import com.hst.scrapper.modules.user.domain.exception.AuthorizationFailException
import com.hst.scrapper.modules.user.domain.exception.UserAlreadyExistException
import com.hst.scrapper.modules.user.domain.exception.UserNotFoundException
import com.hst.scrapper.modules.user.domain.repo.AuthTokenRepository
import com.hst.scrapper.modules.user.domain.repo.UserRepository
import com.hst.scrapper.modules.user.ui.request.SigninRequest
import com.hst.scrapper.modules.user.ui.request.SignupRequest
import com.hst.scrapper.modules.user.ui.response.SigninResponse
import com.hst.scrapper.modules.user.ui.response.UserResponse
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

    private fun getUserByLoginId(loginId: String): User {
        return userRepository.findByLoginId(loginId) ?: throw UserNotFoundException().addAttribute("loginId", loginId)
    }

    /**
     * [id]에 해당하는 유저 조회
     */
    fun getUser(id: Long): User {
        return userRepository.findById(id) ?: throw UserNotFoundException().addAttribute("id", id)
    }

    /**
     * ID, Password 기반 인증
     */
    fun signin(request: SigninRequest): SigninResponse {
        val user = getUserByLoginId(request.loginId)
        if (!passwordEncoder.matches(request.loginPassword, user.loginPassword)) {
            throw AuthorizationFailException()
        }
        return SigninResponse(authTokenRepository.publishToken(user), UserResponse.fromEntity(user))
    }

    /**
     * 사이트 자체 회원가입
     */
    fun signup(request: SignupRequest) {
        if (userRepository.findByLoginId(request.loginId) != null) {
            throw UserAlreadyExistException().addAttribute("loginId", request.loginId)
        }
        if (userRepository.findByEmail(request.email) != null) {
            throw UserAlreadyExistException().addAttribute("email", request.email)
        }
        val user = User.createUser(
            request.loginId,
            passwordEncoder.encode(request.loginPassword),
            request.email,
            request.name
        )
        userRepository.save(user)
    }
}