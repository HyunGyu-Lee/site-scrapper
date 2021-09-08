package com.hst.scrapper.user.infrastructure.jwt

import com.hst.scrapper.user.domain.repo.AuthTokenRepository
import com.hst.scrapper.user.domain.entity.User
import com.hst.scrapper.global.utils.*
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
class JwtAuthTokenRepository(
    val jwtProperties: JwtProperties
) : AuthTokenRepository {
    val log = LoggerFactory.getLogger(JwtAuthTokenRepository::class.java)

    override fun publishToken(user: User): String {
        val now = LocalDate.now()
        val claims = Jwts.claims().setSubject("${user.id}")
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(toDate(now))
            .setExpiration(toDate(now.plusDays(jwtProperties.expireDay)))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .compact()
    }

    override fun getToken(request: HttpServletRequest): String? {
        val token = request.getHeader(AUTHORIZATION_HEADER)
        if (token.isBlank()) {
            log.warn("Authorization Token is empty")
            return null
        } else if (!token.startsWith(TOKEN_TYPE)) {
            log.warn("Authorization Token is not bearer type")
            return null
        }
        return token.substring(TOKEN_TYPE.length + 1)
    }

    override fun isValidToken(token: String?): Boolean {
        if (token == null || token.isBlank()) {
            return false
        }
        val claims = Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(token)
        return claims.body.expiration.before(Date())
    }

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val TOKEN_TYPE = "Bearer"
    }
}