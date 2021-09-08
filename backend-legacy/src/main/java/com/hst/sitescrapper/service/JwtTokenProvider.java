package com.hst.sitescrapper.service;

import com.hst.sitescrapper.constants.GlobalConstants;
import com.hst.sitescrapper.utils.TimeUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	private final String secretKey;

	public JwtTokenProvider(@Value("${spring.jwt.secret-key}") String secretKey) {
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	/***
	 * 새 토큰 발급
	 *
	 * @param userId 토큰 발급할 사용자의 id
	 * @param data payload 내 실 데이터
	 * @return 토큰
	 */
	public <T> String createToken(Long userId, T data) {
		LocalDateTime now = LocalDateTime.now();
		Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
		claims.put("data", data);

		return Jwts.builder()
				.setClaims(claims)	// claims 세팅을 위에서 해주는게 중요
				.setIssuedAt(TimeUtils.toDate(now))
				.setExpiration(TimeUtils.toDate(now.plusDays(1)))
				.signWith(SignatureAlgorithm.HS256, this.secretKey)
				.compact();
	}

	/***
	 * JWT Token 에서 userId 획득
	 * @param token JWT Token
	 * @return 토큰 내 userId
	 */
	public String parseUserId(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	/***
	 * HTTP 요청에서 JWT Token 획득
	 * @param request HTTP 요청
	 * @return JWT 토큰
	 */
	public String resolveToken(HttpServletRequest request) {
		String token = request.getHeader(GlobalConstants.JWT.AUTHORIZATION_HEADER);
		if (StringUtils.isEmpty(token)) {
			logger.error("The given token is empty!");
			return null;
		} else if (!token.startsWith(GlobalConstants.JWT.Bearer)) {
			logger.error("The given token is not bearer type!");
			return null;
		}
		return token.substring(GlobalConstants.JWT.Bearer.length() + 1);
	}

	/***
	 * JWT Token 유효성 검사
	 * @param token JWT token
	 * @return 유효 여부
	 */
	public boolean validateToken(String token) {
		try {
			if (StringUtils.isEmpty(token)) {
				logger.error("The given token is empty!");
				return false;
			}
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			logger.info("{}", claims);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			logger.error(String.format("Invalid Token %s", token), e);
			return false;
		}
	}
}
