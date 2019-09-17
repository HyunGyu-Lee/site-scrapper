package com.hst.sitescrapper.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 인증 flow
 * -> 로그인 성공 시 JWT subject 에 인증한 사용자의 id (userId) 정보 저장
 * -> 토큰 파싱하여 userId 획득 -> DB 에서 사용자 정보 조
 *
 * @author dlgusrb0808@gmail.com
 */
@Service
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	private final String secretKey;

	public JwtTokenProvider(@Value("${spring.jwt.secret-key}") String secretKey) {
		this.secretKey = secretKey;
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
				.setExpiration(Date.from(now.plusDays(1).atZone(ZoneId.systemDefault()).toInstant()))
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, this.secretKey)
				.compact();
	}

	/***
	 * 토큰 -> Object 로 변환
	 * @param token JWT token
	 * @return payload 내 실 데이터
	 */
	public Long parseToken(String token) {
		String userId = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		return Long.valueOf(userId);
	}

	/***
	 * 토큰 유효성 검사
	 * @param token JWT token
	 * @return 유효 여부
	 */
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			logger.error(String.format("Invalid Token %s", token), e);
			return false;
		}
	}
}
