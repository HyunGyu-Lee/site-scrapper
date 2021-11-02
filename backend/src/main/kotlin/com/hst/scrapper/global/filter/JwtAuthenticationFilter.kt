package com.hst.scrapper.global.filter

import com.hst.scrapper.modules.user.application.UserService
import com.hst.scrapper.modules.user.domain.repo.AuthTokenRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author dlgusrb0808@gamil.com
 */
class JwtAuthenticationFilter(
    val authTokenRepository: AuthTokenRepository,
    val userService: UserService
) : OncePerRequestFilter() {

    companion object {
        val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info("Check authentication for {}", request.requestURI)
        val token = authTokenRepository.getToken(request)
        if (authTokenRepository.isValidToken(token)) {
            val userId = authTokenRepository.getUserIdFromToken(token!!)
            val userDetail = userService.getUser(userId)
            val authentication = UsernamePasswordAuthenticationToken(userDetail, userDetail.password, userDetail.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

}