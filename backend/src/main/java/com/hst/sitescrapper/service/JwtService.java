package com.hst.sitescrapper.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author dlgusrb0808@gmail.com
 */
@Service
public class JwtService {

	private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

	private final String secretKey;
	private byte[] keySerial;

	public JwtService(@Value("TODO_FROM_YML") String secretKey) {
		this.secretKey = secretKey;
		this.keySerial = secretKey.getBytes(StandardCharsets.UTF_8);
	}

	/***
	 * 새 토큰 발급
	 *
	 * @param key payload 내 실 데이터의 key
	 * @param data payload 내 실 데이터
	 * @param subject 토큰 구분명
	 * @return 토큰
	 */
	public <T> String createToken(String key, T data, String subject) {
		LocalDateTime now = LocalDateTime.now();;
		return Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
				.setSubject(subject)
				.setExpiration(Date.from(now.plusDays(1).atZone(ZoneId.systemDefault()).toInstant()))
				.claim(key, data)
				.signWith(SignatureAlgorithm.HS256, this.keySerial).compact();
	}

	/***
	 * 토큰 -> Object 로 변환
	 * @param jwt 토큰 문자열
	 * @param key payload 내 실 데이터의 key
	 * @param subject 토큰 구분명
	 * @param clazz payload 내 실 데이터의 타입
	 * @return payload 내 실 데이터
	 */
	public <T> T parseToken(String jwt, String key, String subject, Class<T> clazz) {
		Claims body = Jwts.parser().requireSubject(subject).setSigningKey(keySerial).parseClaimsJws(jwt).getBody();
		return body.get(key, clazz);
	}

	/***
	 * 토큰 유효성 검사
	 * @param jwt 토큰 문자열
	 * @param subject 토큰 구분명
	 * @return
	 */
	public boolean isValidToken(String jwt, String subject) {
		try {
			Jwts.parser().requireSubject(subject).setSigningKey(keySerial).parseClaimsJws(jwt);
		} catch (Exception e) {
			logger.error(String.format("Invalid Token %s", jwt), e);
			return false;
		}

		return true;
	}

}
